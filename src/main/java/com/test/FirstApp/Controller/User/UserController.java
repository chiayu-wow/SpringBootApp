package com.test.FirstApp.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.FirstApp.Repository.UserRepository;
import com.test.FirstApp.Model.User;


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

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/user/login";
    }
}
