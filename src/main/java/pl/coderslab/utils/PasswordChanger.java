package pl.coderslab.utils;


import javassist.NotFoundException;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.coderslab.entity.User;
import pl.coderslab.exception.UniqueValuesException;
import pl.coderslab.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Data
public class PasswordChanger {
    private String currentPassword = "";
    private String newPassword = "";




    public List<ObjectError> validatePasswords(){
        List<ObjectError> errors = new ArrayList<>();
        
        if (newPassword == null) {
            errors.add(new ObjectError("newPassword", "Enter new password"));
        }
        if (newPassword.length() < 5 || newPassword.length() > 20) {
            errors.add(new ObjectError("newPassword", "New password must contain 5 to 20 charters"));
        }
        if(newPassword.equals(currentPassword)){
            errors.add(new ObjectError("newPassword", "Password are the same"));
        }
        if (currentPassword == null) {
            errors.add(new ObjectError("currentPassword", "Enter current password"));
        }
        if (currentPassword.length() < 5 || currentPassword.length() > 20) {
            errors.add(new ObjectError("currentPassword", "Current password is incorrect"));
        }
        return errors;
    }

}
