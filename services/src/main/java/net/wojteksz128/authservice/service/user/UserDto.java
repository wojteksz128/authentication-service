package net.wojteksz128.authservice.service.user;

import lombok.Data;

import java.util.Set;

@Data
@SuppressWarnings("WeakerAccess")
public class UserDto {

    private Long id;
    private String login;
    private Set<RoleDto> roles;
}
