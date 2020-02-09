package com.training.platform.validators;

import javax.validation.*;
import com.training.platform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    private UserService userService;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !userService.isEmailAlreadyInUse(value);
    }
}
