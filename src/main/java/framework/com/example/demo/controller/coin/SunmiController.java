package framework.com.example.demo.controller.coin;

import framework.com.example.demo.model.coin.Unit;
import framework.com.example.demo.model.coin.sunmi;
import framework.com.example.demo.service.coin.SunmiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
public class SunmiController extends CoinController<sunmi> {

    @Autowired
    private SunmiLogicService sunmiLogicService;

    @RequestMapping("/home/seonmi")
    public ModelAndView seonmi() throws IOException, URISyntaxException, ScriptException, InterruptedException {
        ModelAndView mav = new ModelAndView("/home/seonmi");
        Unit<sunmi> unit = sunmiLogicService.getCoin();

        mav.addObject("unit",unit);

        return mav;
    }

    @RequestMapping("/home/soldiers")
    public ModelAndView soldiers() throws IOException, URISyntaxException, ScriptException, InterruptedException {
        ModelAndView mav = new ModelAndView("/home/soldiers");
        Unit<sunmi> unit = sunmiLogicService.getCoin();

        mav.addObject("unit",unit);

        return mav;
    }



}
