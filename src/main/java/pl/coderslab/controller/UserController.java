package pl.coderslab.controller;


import javassist.NotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public UserController(UserService userService, Validator validator, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.validator = validator;
    }


    //============================================
    //          READ CURRENTLY LOGGED USER
    //============================================
    @GetMapping("/app/user")
    public String readSelf(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        User user = currentUser.getUser();
        model.addAttribute(user);
        return "user/selfProfile";
    }


    //============================================
    //          READ SELECTED USER
    //============================================
    @GetMapping("/app/user/{id:\\d+}")
    public String readSelected(@PathVariable Long id, HttpServletResponse response, Model model) throws IOException {
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

    //  REGISTER FORM
    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user", new User());
        return"register";
    }

    //  SAVE NEW USER
    @PostMapping("/register")
    public String registerAction(@ModelAttribute @Valid User user, BindingResult result){
        if (result.hasErrors()) return "register";
        try{
            userService.saveNewUser(user);
            return "redirect:app/dashboard";
        }catch (UniqueValuesException e){
            result.addError(new ObjectError(e.getObjectName(),e.getMessage()));
            return "register";
        }
    }


    //============================================
    //          UPDATE USER
    //============================================

    //  UPDATE USER FORM
    @GetMapping("/app/user/edit")
    public String updateForm(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        User user = currentUser.getUser();
        User userToEdit = new User();
        userToEdit.setUsername(user.getUsername());
        userToEdit.setEmail(user.getEmail());
        userToEdit.setCity(user.getCity());
        userToEdit.setCountry(user.getCountry());
        userToEdit.setId(user.getId());
        userToEdit.setRoles(user.getRoles());
        userToEdit.setCreated(user.getCreated());
        userToEdit.setPassword("");

        model.addAttribute("user",userToEdit);

        return"user/edit";
    }

    //  SAVE UPDATED USER
    @PostMapping("/app/user/edit")
    public String updateAction(@AuthenticationPrincipal CurrentUser currentUser,
                               @ModelAttribute("user") @Valid User editedUser, BindingResult result){
        if (result.hasErrors()) return "user/edit";
        User user = currentUser.getUser();
        editedUser.setId(user.getId());
        editedUser.setRoles(user.getRoles());
        editedUser.setCreated(user.getCreated());
        editedUser.setEnabled(user.getEnabled());

        if(userService.checkPassword(editedUser.getPassword(),user.getPassword())){
            editedUser.setPassword(user.getPassword());
        }else {
            result.addError(new ObjectError("password","Password incorect"));
            return "user/edit";
        }
        try{
            user = userService.saveEditedUser(editedUser);
            return "redirect:/app/dashboard";
        }catch (UniqueValuesException e){
            result.addError(new ObjectError(e.getObjectName(),e.getMessage()));
            return "user/edit";
        }
    }





    //  WORK IN PROGRESS



    //============================================
    //          CHANGE PASSWORD
    //============================================

    //  CHANGE PASSWORD FORM
    @GetMapping("/app/user/change-password")
    public String changePasswordForm(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        User user = currentUser.getUser();
        user.setPassword("");
        model.addAttribute(user);
        return"edit";
    }

    //  SAVE CHANGED PASSWORD
    @PostMapping("/app/user/change-password")
    public String changePasswordAction(@ModelAttribute @Valid User user, BindingResult result){
        if (result.hasErrors()) return "user/edit";
        try{
            userService.saveNewUser(user);
            return "redirect:app/dashboard";
        }catch (UniqueValuesException e){
            result.addError(new ObjectError(e.getObjectName(),e.getMessage()));
            return "user/edit";
        }
    }

}
