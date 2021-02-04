package pl.coderslab.service;


import javassist.NotFoundException;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.coderslab.entity.User;
import pl.coderslab.exception.UniqueValuesException;

@Data
public class PasswordChanger {
    private String currentPassword;
    private String newPassword;

    public PasswordChanger(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }



    public void validatePasswords(BindingResult result, String username, UserService userService){
        if (newPassword == null) {
            result.addError(new ObjectError("newPassword", "Enter new password"));
        }
        if (newPassword.length() < 5 || newPassword.length() > 20) {
            result.addError(new ObjectError("newPassword", "New password must contain 5 to 20 charters"));
        }
        if(newPassword.equals(currentPassword)){
            result.addError(new ObjectError("newPassword", "Password are the same"));
        }
        if (currentPassword == null) {
            result.addError(new ObjectError("currentPassword", "Enter current password"));
        }
        if (currentPassword.length() < 5 || currentPassword.length() > 20) {
            result.addError(new ObjectError("currentPassword", "Current password is incorrect"));
        }
        if(!userService.checkPassword(currentPassword, userService.findByUserName(username).getPassword())){
            result.addError(new ObjectError("currentPassword", "Current password is incorrect"));
        }
    }

    public void saveNewPassword(User user, UserService userService){
        user.setPassword(userService.encodePass(newPassword));
        try {
            userService.saveEditedUser(user);
        } catch (UniqueValuesException e) {
            //no unique values edited
        }
    }

}
