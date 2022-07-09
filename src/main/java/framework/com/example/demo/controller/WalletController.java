package framework.com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/wallet")
public class WalletController {
    @RequestMapping("/wallet")
    public ModelAndView test(){

        ModelAndView mav = new ModelAndView("/wallet/wallet");

        return mav;
    }

}
