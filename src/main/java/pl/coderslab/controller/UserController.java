package pl.coderslab.controller;


import javassist.NotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import pl.coderslab.entity.User;
import pl.coderslab.exception.UniqueValuesException;
import pl.coderslab.utils.CurrentUser;
import pl.coderslab.utils.PasswordChanger;
import pl.coderslab.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/app/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    //============================================
    //          READ CURRENTLY LOGGED USER
    //============================================
    @GetMapping("")
    public String readSelf(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        User user = currentUser.getUser();
        userService.loadLast10Trips(user);
        model.addAttribute(user);
        return "app/user/ownProfile";
    }


    //============================================
    //          READ SELECTED USER
    //============================================
    @GetMapping("/{id:\\d+}")
    public String readSelected(@PathVariable Long id, HttpServletResponse response,
                               @AuthenticationPrincipal CurrentUser currentUser, Model model) throws IOException {
        if (currentUser.getUser().getId() == id) return "redirect:/app/user";
        try {
            User user = userService.findById(id);
            userService.loadLast10Trips(user);
            model.addAttribute(user);
        } catch (NotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        return "app/user/selectedProfile";
    }


    //============================================
    //          UPDATE USER
    //============================================
    @GetMapping("/edit")
    public String updateForm(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        User user = currentUser.getUser();
        User userToEdit = new User();
        userToEdit.setUsername(user.getUsername());
        userToEdit.setEmail(user.getEmail());
        userToEdit.setCity(user.getCity());
        userToEdit.setCountry(user.getCountry());
        model.addAttribute("user", userToEdit);

        return "app/user/edit";
    }
    @PostMapping("/edit")
    public String updateAction(@AuthenticationPrincipal CurrentUser currentUser,
                               @ModelAttribute("user") @Valid User modelUser, BindingResult result) {
        if (result.hasErrors()) return "app/user/edit";
        User user = currentUser.getUser();

        user.setUsername(modelUser.getUsername());
        user.setEmail(modelUser.getEmail());
        user.setCity(modelUser.getCity());
        user.setCountry(modelUser.getCountry());

        if (!userService.checkPassword(modelUser.getPassword(), user.getPassword())) {
            result.addError(new ObjectError("password", "Password incorect"));
            return "app/user/edit";
        }
        try {
            userService.saveEditedUser(user);
            return "redirect:/app/dashboard";
        } catch (UniqueValuesException e) {
            result.addError(new ObjectError(e.getObjectName(), e.getMessage()));
            return "app/user/edit";
        }
    }


    //============================================
    //          CHANGE PASSWORD
    //============================================
    @GetMapping("/change-password")
    public String changePasswordForm(Model model) {
        model.addAttribute("passwordChanger", new PasswordChanger());
        return "app/user/changePassword";
    }
    @PostMapping("/change-password")
    public String changePasswordAction(@AuthenticationPrincipal CurrentUser currentUser,
                                       @ModelAttribute PasswordChanger passwordChanger, BindingResult result) {
        User user = currentUser.getUser();
        List<ObjectError> errors = userService.validateNewPassword(user.getUsername(), passwordChanger);
        if (result.hasErrors()) {
            return "app/user/changePassword";
        }
        errors.forEach(e -> result.addError(e));
        if (result.hasErrors()) {
            return "app/user/changePassword";
        } else {
            userService.saveNewPassword(user.getUsername(), passwordChanger.getNewPassword());
            return "redirect:/app/dashboard";
        }

    }


    //============================================
    //          DELETE USER
    //============================================
    @GetMapping("/delete-account")
    public String deleteUserForm() {
        return "app/user/confirmDelete";
    }
    @PostMapping("/delete-account")
    public String deleteUserAction(@AuthenticationPrincipal CurrentUser currentUser) {
        User user = currentUser.getUser();
        userService.deleteUser(user);
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/home";

    }


}
