package com.test.FirstApp.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.test.FirstApp.Repository.UserRepository;

import jakarta.servlet.http.HttpSession;

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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);  

        userRepository.save(user);
        return "redirect:/user/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session, Model model) {
        User existingUser = userRepository.findByUsername(user.getUsername());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (existingUser != null && encoder.matches(user.getPassword(), existingUser.getPassword())) {
            session.setAttribute("user", existingUser);
            return "redirect:/home";  
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "redirect:/user/login";  
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/home";
    }
}
