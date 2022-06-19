package framework.com.example.demo.controller.coin;

import framework.com.example.demo.model.coin.Unit;
import framework.com.example.demo.service.coin.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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



}
