package framework.com.example.demo.controller;

import framework.com.example.demo.model.entity.Unit;
import framework.com.example.demo.model.entity.sunmi;
import framework.com.example.demo.service.SunmiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import xyz.hexile.cloudflarescraper.ScraperException;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Controller
public class SunmiController extends CoinController<sunmi> {

    @Autowired
    private SunmiLogicService sunmiLogicService;

    @RequestMapping("/home/seonmi")
    public ModelAndView seonmi() throws IOException, URISyntaxException, ScriptException, ScraperException, InterruptedException {
        ModelAndView mav = new ModelAndView("/home/seonmi");
        Unit<sunmi> unit = sunmiLogicService.getCoin();

        mav.addObject("unit",unit);

        return mav;
    }

    @RequestMapping("/home/soldiers")
    public ModelAndView soldiers() throws IOException, URISyntaxException, ScriptException, ScraperException, InterruptedException {
        ModelAndView mav = new ModelAndView("/home/soldiers");
        Unit<sunmi> unit = sunmiLogicService.getCoin();

        mav.addObject("unit",unit);

        return mav;
    }
}
