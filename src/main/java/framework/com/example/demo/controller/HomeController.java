package framework.com.example.demo.controller;

import framework.com.example.demo.model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping("/")
    public String main(){
        return "redirect:/auth/login";
/*
        return "/home/home001";
*/
    }
    @RequestMapping("/toastr")
    public String toastr(){
        return "toastr";
/*
        return "/home/home001";
*/
    }
    @PostMapping("/SignUp")
    public String SignUp(User user){
        return "redirect:/auth/login";
    }



}
