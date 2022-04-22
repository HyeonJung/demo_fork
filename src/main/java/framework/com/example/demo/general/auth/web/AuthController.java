package framework.com.example.demo.general.auth.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    // 스프링 컨테이너가 생성자를 통해 자동으로 주입한다.

    @RequestMapping( "/auth/check")
    public String Check() throws Exception {
        //return "/index";
        return "redirect:/auth/login";
    }
    @RequestMapping( "/auth/login")
    public String login() throws Exception {
        //return "/index";
        return "auth/login";
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