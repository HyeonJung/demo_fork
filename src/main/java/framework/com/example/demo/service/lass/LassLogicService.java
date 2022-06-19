package framework.com.example.demo.service.lass;

import framework.com.example.demo.domain.lass.LassDataVO;
import framework.com.example.demo.domain.lass.LassVO;
import framework.com.example.demo.service.Scraper.Scraper;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LassLogicService {
    Scraper scraper = new Scraper();
    public LassDataVO GetLassList() throws Exception {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("LoginAction", "login"));
        params.add(new BasicNameValuePair("memId", "soldiers"));
        params.add(new BasicNameValuePair("memPw", "1234"));

        scraper.Go("http://www.lsstation.co.kr/member_login_ok.php", params);
        scraper.Go("http://www.lsstation.co.kr/index.php");
        scraper.Go("http://www.lsstation.co.kr/sub_pension_inch.php");

        String html = scraper.Html;
        Document doc = Jsoup.parseBodyFragment(html);
        ArrayList<LassVO> list =new ArrayList<LassVO>();

        Elements elem  = doc.select("table > tbody > tr > td");
        for(Element e: elem.select("a")) {
            LassVO vo = new LassVO();
            vo.setName(e.text());
            vo.setLoc("인천시");
            vo.setLink("http://www.lsstation.co.kr/" + e.attr("href"));
            list.add(vo);
        }
        LassDataVO result =  new LassDataVO();
        result.data =list;
        return result;
    }

    public void GetHouseInfo(String link) throws Exception{
        scraper.Go(link);

        String result = scraper.Html;

        String a = "";
    }
}
