package framework.com.example.demo.controller;

import framework.com.example.demo.model.dto.MemberDto;
import framework.com.example.demo.model.entity.User;
import framework.com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/lass")
public class LassController {

    @RequestMapping("/lassinfo")
    public ModelAndView LassInfo(@RequestParam String nftcode){

        ModelAndView mav = new ModelAndView("/tiles/view/lass/lassInfo");

        mav.addObject("id","soldiers");
        mav.addObject("pw", "1234");

        return mav;
    }

}
