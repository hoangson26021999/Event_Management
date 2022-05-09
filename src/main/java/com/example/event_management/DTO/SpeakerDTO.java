package com.example.event_management.DTO;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class SpeakerDTO {

    private long id ;
    private String speaker_name ;
    private int speaker_age ;

    @Email
    private String speaker_email ;

    private String speaker_career ;

    @NotEmpty(message = "Thiếu username")
    @Min(value = 8, message = "Tài khoản phải từ 8 kí tự trở lên")
    private String speaker_account_name ;

    @NotEmpty(message = "Thiếu password")
    @Min(value = 8, message = "Password phải từ 8 kí tự trở lên")
    private String speaker_account_password ;

    private List<EventDTO> speaker_events ;
}
