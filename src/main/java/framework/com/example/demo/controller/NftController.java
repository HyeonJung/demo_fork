package framework.com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/nft")
public class NftController {


    @RequestMapping("/makeImage")
    public ModelAndView LassInfo(){

        ModelAndView mav = new ModelAndView("/tiles/view/nft/make");

        return mav;
    }


}
