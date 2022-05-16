package com.example.event_management.validator;

import com.example.event_management.dto.EventDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class EventValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return EventDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "event_name", "NotEmpty.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "event_date", "NotEmpty.date");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "event_starting_time", "NotEmpty.time");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "event_ending_time", "NotEmpty.time");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "event_location", "NotEmpty.location");

    }
}
