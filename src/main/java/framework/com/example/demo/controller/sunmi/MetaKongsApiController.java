package framework.com.example.demo.controller.sunmi;


import framework.com.example.demo.model.network.request.MetaKongsApiRequestDto;
import framework.com.example.demo.model.network.response.MetaKongsApiResponseDto;
import framework.com.example.demo.service.coin.MetaKongfpApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meta-kongs")
public class MetaKongsApiController {
    @Autowired
    private MetaKongfpApiService metaKongfpApiService;


    @PostMapping("/meta-kongs")
    public Long save(@RequestBody MetaKongsApiRequestDto requestDto) {
        return metaKongfpApiService.save(requestDto);
    }

    @ResponseBody
    @PutMapping("/meta-kongs/{fp}")
    public String update(@PathVariable  String fp) {
        metaKongfpApiService.update(fp);
        return "";
    }

    @GetMapping("/meta-kongs")
    public MetaKongsApiResponseDto findByAll(){
        return metaKongfpApiService.findByAll();
    }
}
