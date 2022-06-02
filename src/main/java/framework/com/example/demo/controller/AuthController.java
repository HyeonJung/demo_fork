package framework.com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {
    // 스프링 컨테이너가 생성자를 통해 자동으로 주입한다.

    @RequestMapping( "/check")
    public String Check() throws Exception {
        //return "/index";
        return "redirect:/auth/login";
    }
    @RequestMapping( "/login")
    public String login() throws Exception {
        //return "/index";
        return "auth/login";
    }

    @RequestMapping("/signIn")
    public String SignIn() throws Exception {
        return "auth/registration";
    }




    @GetMapping("/loginform")
    public String loginform(){
        return "members/loginform";
    }

    @RequestMapping("/loginerror")
    public String loginerror(@RequestParam("login_error")String loginError){
        return "members/loginerror";
    }
}