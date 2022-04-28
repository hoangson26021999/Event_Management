package com.example.event_management.validator;

import com.example.event_management.entity.RegisterEntity;
import com.example.event_management.repository.RegisterRepository;
import org.apache.commons.validator.routines.EmailValidator;
import com.example.event_management.DTO.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegisterValidator implements Validator {

    @Autowired
    RegisterRepository registerRepository ;

    // common-validator library.
    private EmailValidator emailValidator = EmailValidator.getInstance();

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

        RegisterDTO appUserForm = (RegisterDTO) target;

        // Kiểm tra các field của AppUserForm.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.appUserForm.userName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.appUserForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.appUserForm.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.appUserForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.appUserForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.appUserForm.confirmPassword");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.appUserForm.gender");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryCode", "NotEmpty.appUserForm.countryCode");

        if (!this.emailValidator.isValid(appUserForm.getRegister_email())) {
            // Email không hợp lệ.
            errors.rejectValue("email", "Pattern.appUserForm.email");
        } else if ( registerRepository.findRegisterEntityByRegisterEmail(appUserForm.getRegister_email()) != null) {
                // Email đã được sử dụng bởi tài khoản khác.
                errors.rejectValue("email", "Duplicate.appUserForm.email");
            }


        if (!errors.hasFieldErrors("userName")) {
            RegisterEntity dbUser = registerRepository.findRegisterEntityByRegisterAccountName(appUserForm.getRegister_account_name());
            if (dbUser != null) {
                // Tên tài khoản đã bị sử dụng bởi người khác.
                errors.rejectValue("userName", "Duplicate.appUserForm.userName");
            }
        }

        if (!errors.hasErrors()) {
            if (!appUserForm.getConfirm_password().equals(appUserForm.getRegister_account_password())) {
                errors.rejectValue("confirmPassword", "Match.appUserForm.confirmPassword");
            }
        }
    }

}


