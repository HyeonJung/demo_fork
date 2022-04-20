package framework.com.example.demo.general.auth.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    @RequestMapping( "/auth/check")
    public String Check() throws Exception {
        return "/index";
    }

}
