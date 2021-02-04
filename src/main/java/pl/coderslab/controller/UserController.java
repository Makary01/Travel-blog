package pl.coderslab.controller;


import javassist.NotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import pl.coderslab.entity.User;
import pl.coderslab.exception.UniqueValuesException;
import pl.coderslab.service.CurrentUser;
import pl.coderslab.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.Validator;
import java.io.IOException;



@Controller
public class UserController {

    private final Validator validator;

    private final UserService userService;

    public UserController(UserService userService, Validator validator) {
        this.userService = userService;
        this.validator = validator;
    }


    //============================================
    //          READ CURRENTLY LOGGED USER
    //============================================
    @GetMapping("/user")
    public String readSelf(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        User user = currentUser.getUser();
        model.addAttribute(user);
        return "user/selfProfile";
    }


    //============================================
    //          READ SELECTED USER
    //============================================
    @GetMapping("/user/{id:\\d+}")
    public String read(@PathVariable Long id, HttpServletResponse response, Model model) throws IOException {
        try {
            User user = userService.findById(id);
            if(user.getEnabled() == 0){
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            model.addAttribute(user);
        } catch (NotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        return "user/profile";
    }


    //============================================
    //          CREATE NEW USER
    //============================================
    @PostMapping("/register")
    public String create(@ModelAttribute @Valid User user, BindingResult result){
        if (result.hasErrors()) return "register";
        try{
            userService.saveUser(user);
            return "redirect:app/dashboard";
        }catch (UniqueValuesException e){
            result.addError(new ObjectError("username",e.getMessage()));
            return "register";
        }
    }


}
