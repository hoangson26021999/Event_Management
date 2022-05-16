package com.example.event_management.validator;

import com.example.event_management.entity.RegisterEntity;
import com.example.event_management.repository.RegisterRepository;
import com.example.event_management.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegisterValidator implements Validator {

    @Autowired
    RegisterRepository registerRepository ;

    @Override
    public boolean supports(Class<?> clazz) {

        return RegisterDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        RegisterDTO appUserForm = (RegisterDTO) target;

        // Kiểm tra các field của AppUserForm.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "register_account_name", "NotEmpty.account_name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "register_name", "NotEmpty.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "register_email", "NotEmpty.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "register_account_password", "NotEmpty.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirm_password", "NotEmpty.confirm_password");


      if ( registerRepository.findRegisterEntityByRegisterEmail(appUserForm.getRegister_email()) != null) {
                // Email đã được sử dụng bởi tài khoản khác.
                errors.rejectValue("register_email", "Duplicate.email");
            }

        if (!errors.hasFieldErrors("userName")) {
            RegisterEntity dbUser = registerRepository.findRegisterEntityByRegisterAccountName(appUserForm.getRegister_account_name());
            if (dbUser != null) {
                // Tên tài khoản đã bị sử dụng bởi người khác.
                errors.rejectValue("register_account_name", "Duplicate.account_name");
            }
        }

        if (!errors.hasErrors()) {
            if (!appUserForm.getConfirm_password().equals(appUserForm.getRegister_account_password())) {
                errors.rejectValue("confirm_password", "Match.confirm_password");
            }
        }
    }

}


