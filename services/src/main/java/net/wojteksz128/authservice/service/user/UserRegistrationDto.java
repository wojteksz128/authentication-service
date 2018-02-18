package net.wojteksz128.authservice.service.user;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationDto {

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 6)
    private String password;

    @NotEmpty
    @Size(min = 6)
    private String confirmPassword;

    @AssertTrue
    private Boolean terms;
}
