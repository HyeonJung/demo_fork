package framework.com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/game")
public class GameController {
    @RequestMapping("/random")
    public ModelAndView random(){

        ModelAndView mav = new ModelAndView("/game/random");

        return mav;
    }
}
