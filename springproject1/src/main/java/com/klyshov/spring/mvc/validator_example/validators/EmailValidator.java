package com.klyshov.spring.mvc.validator_example.validators;

/**
 * Created by 16688641 on 23.10.2019.
 */
import com.klyshov.spring.mvc.validator_example.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class EmailValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User)target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "","Email is empty");
        if (!user.getEmail().contains("@")) {
            errors.rejectValue("email","", "Email is not valid.");
        }
    }
}