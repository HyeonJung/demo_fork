package framework.com.example.demo.general.auth.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AuthController {
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

}