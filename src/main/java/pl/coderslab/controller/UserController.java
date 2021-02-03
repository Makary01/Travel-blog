package pl.coderslab.controller;


import javassist.NotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.entity.User;
import pl.coderslab.service.CurrentUser;
import pl.coderslab.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Optional;


@Controller
@RequestMapping("/user")
public class UserController {

    private final Validator validator;

    private final UserService userService;

    public UserController(UserService userService, Validator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @GetMapping("")
    public String readSelf(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        User user = currentUser.getUser();
        model.addAttribute(user);
        return "user/selfProfile";
    }

    @GetMapping("/{id:\\d+}")
    public String read(@PathVariable Long id, HttpServletResponse response, Model model) throws IOException {
        try {
            User user = userService.findById(id);
            model.addAttribute(user);
        } catch (NotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        return "user/profile";
    }


}
