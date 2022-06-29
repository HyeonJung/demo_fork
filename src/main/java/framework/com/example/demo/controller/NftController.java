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

    @RequestMapping("/tstminting")
    public ModelAndView tstMinting(){

        ModelAndView mav = new ModelAndView("/nft/minting");

        return mav;
    }
    @RequestMapping("/ticket")
    public ModelAndView ticket(){

        ModelAndView mav = new ModelAndView("/nft/ticket");

        return mav;
    }

    @RequestMapping("/transfer")
    public ModelAndView transfer(){

        ModelAndView mav = new ModelAndView("/nft/transfer");

        return mav;
    }

    @RequestMapping("/random")
    public ModelAndView random(){

        ModelAndView mav = new ModelAndView("/nft/random");

        return mav;
    }


}
