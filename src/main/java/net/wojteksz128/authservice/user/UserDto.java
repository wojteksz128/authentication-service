package net.wojteksz128.authservice.user;

import lombok.Data;

import java.util.Set;

@Data
@SuppressWarnings("WeakerAccess")
public class UserDto {

    private Long id;
    private String email;
    private Set<RoleDto> roles;
}
