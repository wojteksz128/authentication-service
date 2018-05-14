package net.wojteksz128.authservice.service.user;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Date;

@Data
public class UserPersonalDataDto {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private Date birthDate;

    @Email
    @NotEmpty
    private String email;

    private String url;

    private String phoneNumber;

    private UserDto user;
}
