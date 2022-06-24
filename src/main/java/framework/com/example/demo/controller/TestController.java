package framework.com.example.demo.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import framework.com.example.demo.attribute.AttrDetail;
import framework.com.example.demo.attribute.dao.mapper.AttributeMapper;
import framework.com.example.demo.attribute.dto.DetailVO;
import framework.com.example.demo.attribute.dto.HeaderVO;
import framework.com.example.demo.attribute.service.AttrDetailService;
import framework.com.example.demo.attribute.service.AttrHeaderService;
import framework.com.example.demo.service.Scraper.Scraper;
import framework.com.example.demo.slack.dto.SlackVO;
import framework.com.example.demo.slack.service.SlackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
public class TestController {
    @Autowired
    private AttrHeaderService headerService;

    @Autowired
    private AttrDetailService detailService;

    @Autowired
    private  AttributeMapper attributeMapper;

    @GetMapping("/api/mts")
    public void test(@RequestParam String keyword) throws Exception {
        IntStream.range(1, 6000).parallel().forEach(index ->{
            Scraper scraper = new Scraper();
            try {
                scraper.Go("https://data.metatoydragonz.io/" + keyword + "/" + index+ ".json");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            JsonObject tokenInfo = gson.fromJson(scraper.Html, JsonObject.class);
            String tokenId= tokenInfo.getAsJsonObject().get("name").getAsString().split("#")[1];
            String nftCode= tokenInfo.getAsJsonObject().get("name").getAsString().split("#")[0];

            JsonArray attributes = new JsonArray();
            attributes = tokenInfo.getAsJsonObject().get("attributes").getAsJsonArray();

            for (JsonElement data:
                    attributes) {
                String type = data.getAsJsonObject().get("trait_type").getAsString();
                String value = data.getAsJsonObject().get("value").getAsString();


                    HeaderVO headerVO = new HeaderVO();
                    headerVO.setHeader(type);
                    headerVO.setNftCode(nftCode);
                    headerService.insert(headerVO);


                DetailVO vo = new DetailVO();
                vo.setDetail(value);
                vo.setHeader(type);
                vo.setNftCode(nftCode);
                vo.setTokenId(tokenId);
                vo.setIdx(String.valueOf(index));

                detailService.insert(vo);
            }

            try {
                SlackVO slackVO = new SlackVO();
                slackVO.setWebhookUrl("https://hooks.slack.com/services/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");
                slackVO.setText("tokenID=" +index + "[수집완료]");
                SlackService<SlackVO> slackService = null;
                slackService = new SlackService<SlackVO>();
                slackService.SetMethod(SlackService.Method.POST);
                slackService.SetJsonBody(slackVO);
                slackService.SendSlackMessage(slackVO.getWebhookUrl(), slackVO);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    @GetMapping("/api/mtsList")
    public  List<Map<String, Object>>  test2() throws Exception {
        List<Map<String, Object>> result = attributeMapper.getAllAttributeList();

        return result;
    }

}
