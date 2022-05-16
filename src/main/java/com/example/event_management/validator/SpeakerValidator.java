package com.example.event_management.validator;

import com.example.event_management.dto.SpeakerDTO;
import com.example.event_management.entity.SpeakerEntity;
import com.example.event_management.repository.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Service
public class SpeakerValidator implements Validator {

    @Autowired
    SpeakerRepository speakerRepository ;

    @Override
    public boolean supports(Class<?> clazz) {
        return SpeakerDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        SpeakerDTO appUserForm = (SpeakerDTO) target;

        // Kiểm tra các field của AppUserForm.

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "speaker_name", "NotEmpty.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "speaker_email", "NotEmpty.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "speaker_career", "NotEmpty.career");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "speaker_account_name", "NotEmpty.account_name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "speaker_account_password", "NotEmpty.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirm_password", "NotEmpty.confirm_password");


        if ( speakerRepository.findSpeakerEntityBySpeakerEmail(appUserForm.getSpeaker_email()) != null) {
            // Email đã được sử dụng bởi tài khoản khác.
            errors.rejectValue("speaker_email", "Duplicate.email");
        }

        if (!errors.hasFieldErrors("userName")) {
            SpeakerEntity dbUser = speakerRepository.findSpeakerEntityBySpeakerAccountName(appUserForm.getSpeaker_account_name());
            if (dbUser != null) {
                // Tên tài khoản đã bị sử dụng bởi người khác.
                errors.rejectValue("speaker_account_name", "Duplicate.account_name");
            }
        }

        if (!errors.hasErrors()) {
            if (!appUserForm.getConfirm_password().equals(appUserForm.getSpeaker_account_password())) {
                errors.rejectValue("confirm_password", "Match.confirm_password");
            }
        }
    }
}
