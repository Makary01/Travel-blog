package pl.coderslab.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.User;
import pl.coderslab.exception.UniqueValuesException;
import pl.coderslab.service.UserService;
import pl.coderslab.utils.CurrentUser;

import javax.validation.Valid;
import java.util.Optional;

@Transactional
@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    //============================================
    //          WELCOME PAGE
    //============================================
    @GetMapping({"/home","/*"})
    public String home(@AuthenticationPrincipal CurrentUser currentUser){
        return isLoggedId(currentUser) ? "redirect:/app/dashboard" : "home";
    }

    //============================================
    //          ABOUT APP PAGE
    //============================================
    @GetMapping("/about")
    public String about(@AuthenticationPrincipal CurrentUser currentUser){
        return isLoggedId(currentUser) ? "redirect:/app/dashboard" : "about";
    }

    //============================================
    //          REGISTRATION
    //============================================
    @GetMapping("/register")
    public String registerForm(Model model,@AuthenticationPrincipal CurrentUser currentUser) {
        if(isLoggedId(currentUser)) return  "redirect:/app/dashboard";
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


    private Boolean isLoggedId(CurrentUser currentUser){
        Optional<CurrentUser> currentUserOptional = Optional.ofNullable(currentUser);
        if(currentUserOptional.isPresent()){
            Optional<User> userOptional = Optional.ofNullable(currentUserOptional.get().getUser());
            return userOptional.isPresent();
        }
        return false;
    }
}
