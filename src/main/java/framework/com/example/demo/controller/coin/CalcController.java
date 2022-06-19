package framework.com.example.demo.controller.coin;

import framework.com.example.demo.model.coin.Unit;
import framework.com.example.demo.service.coin.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
public class CalcController {

    @Autowired
    private CoinService coinService;

    @RequestMapping("/home/seonmi")
    public ModelAndView seonmi() throws IOException, URISyntaxException, ScriptException, InterruptedException {
        ModelAndView mav = new ModelAndView("/home/seonmi");
        Unit unit = coinService.getCoin();

        mav.addObject("unit",unit);

        return mav;
    }

    @RequestMapping("/home/soldiers")
    public ModelAndView soldiers() throws IOException, URISyntaxException, ScriptException, InterruptedException {
        ModelAndView mav = new ModelAndView("/home/soldiers");
        Unit unit = coinService.getCoin();

        mav.addObject("unit",unit);

        return mav;
    }



    //나중에 옮기기 다른컨트롤러로
    //더 솔져스 하루 이동물량 수집
    @GetMapping("/api/gettsodayamount")
    public void getTSODayAmount() throws IOException, InterruptedException {
        coinService.GetTsoDayAmount();
    }

    //베이비몽즈 하루 이동물량 수집
    @GetMapping("/api/getbmzdayamount")
    public void getBmzDayAmount() throws IOException, InterruptedException {
        coinService.GetBmzDayAmount();
    }

    //제네시스몽즈 하루 이동물량 수집
    @GetMapping("/api/getmgdayamount")
    public void getMGDayAmount() throws IOException, InterruptedException {
        coinService.GetMGDayAmount();
    }



}
