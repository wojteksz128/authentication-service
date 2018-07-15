package net.wojteksz128.authservice.service.user;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationDto {

    @NotEmpty
    private String login;

    @NotEmpty
    @Size(min = 6)
    private String password;

    @NotEmpty
    @Size(min = 6)
    private String confirmPassword;

    @NotNull
    @Valid
    private UserPersonalDataDto personalData;

    @AssertTrue
    private Boolean terms;
}
