package com.example.event_management.validator;

import com.example.event_management.dto.PresentationDTO;
import com.example.event_management.dto.RegisterDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component

public class PresentationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PresentationDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "presentation_name", "NotEmpty.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "presentation_content", "NotEmpty.content");

    }
}
