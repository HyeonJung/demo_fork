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
@RequestMapping("/")
public class HomeController {
    @Autowired
    private  MemberService memberService;

    @RequestMapping("/")
    public String main(){
        return "redirect:/auth/login";
    }
    @PostMapping("/SignUp")
    public String SignUp(User user){
        return "redirect:/auth/login";
    }

    @PostMapping("/sampleSignUp")
    public String sampleSignUp(MemberDto memberDto){
        memberService.joinUser(memberDto);

        return "redirect:/auth/login";
    }

    @RequestMapping("/sampleLogin")
    public String sampleLogin(MemberDto memberDto){
        return "redirect:/mainPage";
    }

    @RequestMapping("/home/mainPage")
        public String mainPage(){
            return "/home/index";
    }

    @RequestMapping("/home/sample1")
    public String sample1(){
        return "/tiles/view/home/sample1";
    }

    @RequestMapping("/home/nftinfo")
    public ModelAndView nftinfo(@RequestParam String nftcode){
        ModelAndView mav = new ModelAndView("/tiles/view/home/nftinfo");

        mav.addObject("code",nftcode);

        return mav;
    }

    @RequestMapping("/home/holderinfo")
    public ModelAndView holderInfo(@RequestParam String nftcode){
        ModelAndView mav = new ModelAndView("/tiles/view/home/holderInfo");
        mav.addObject("code",nftcode);

        return mav;
    }

    @RequestMapping("/toastr")
    public String toastr(){
        return "toastr";
    }

    @RequestMapping("/dynamictable")
    public String dynamictable(){
        return "/demo/dynamic_table";
    }
}
