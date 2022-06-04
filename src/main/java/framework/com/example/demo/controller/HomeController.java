package framework.com.example.demo.controller;

import framework.com.example.demo.model.dto.MemberDto;
import framework.com.example.demo.model.entity.User;
import framework.com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String nftinfo(){
        return "/tiles/view/home/nftinfo";
    }

    @RequestMapping("/home/holderinfo")
    public String holderInfo(){
        return "/tiles/view/home/holderInfo";
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
