package net.wojteksz128.authservice.service.user;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.Set;

@Data
@SuppressWarnings("WeakerAccess")
public class UserDto {

    private Long id;

    @NotEmpty
    private String login;

    private Set<RoleDto> roles;

    @Valid
    private UserPersonalDataDto personalData;
}
