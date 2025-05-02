package com.test.FirstApp.Controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @GetMapping("/login")
    public String loginPage() {
        return "/User/login"; 
    }

    @GetMapping("/register")
    public String registerPage() {
        return "/User/register"; 
    }
}
