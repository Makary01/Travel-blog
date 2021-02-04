package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.entity.User;

@Controller
public class HomeController {

    //============================================
    //              WELCOME PAGE
    //============================================
    @GetMapping("/home")
    public String home(){

        return"home";
    }

    @GetMapping("/app/dashboard")
    public String dashboard(){

        return "app/dashboard";
    }

}
