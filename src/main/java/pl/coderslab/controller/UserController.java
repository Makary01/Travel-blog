package pl.coderslab.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.service.UserService;

import javax.validation.Validator;


import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Validator validator;

    private final UserService userService;

    public UserController(UserService userService, Validator validator) {
        this.userService = userService;
        this.validator = validator;
    }



}
