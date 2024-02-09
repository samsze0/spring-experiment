package com.example.validators;

import com.example.model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> cls) {
        return User.class.equals(cls);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (user.getName() == null || user.getName().length() < 6 || user.getName().length() > 32) {
            errors.rejectValue("name", "Size.userForm.name");
        }

        if (user.getAge() == null || user.getAge() < 0 || user.getAge() > 100) {
            errors.rejectValue("age", "Size.userForm.age");
        }
    }
}