package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.entity.User;

@Controller
public class HomeController {

    //============================================
    //          MAIN PAGE
    //============================================
    @GetMapping("/home")
    public String home(){

        return"home";
    }

    //============================================
    //          REGISTRATION FORM
    //============================================
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return"register";
    }
}
