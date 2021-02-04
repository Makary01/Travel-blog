package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.User;
import pl.coderslab.exception.UniqueValuesException;
import pl.coderslab.service.UserService;

import javax.validation.Valid;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    //============================================
    //          WELCOME PAGE
    //============================================
    @GetMapping({"/home",""})
    public String home(){

        return"home";
    }

    //============================================
    //          REGISTRATION
    //============================================
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String registerAction(@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) return "register";
        if (user.getPassword().length() < 5 || user.getPassword().length() > 20) {
            result.addError(new ObjectError("password", "Password must contain 5 to 20 charters"));
            return "register";
        }
        try {
            userService.saveNewUser(user);
            return "redirect:app/dashboard";
        } catch (UniqueValuesException e) {
            result.addError(new ObjectError(e.getObjectName(), e.getMessage()));
            return "register";
        }
    }

}
