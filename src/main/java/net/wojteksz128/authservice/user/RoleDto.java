package net.wojteksz128.authservice.user;

import lombok.Data;


@Data
@SuppressWarnings("WeakerAccess")
public class RoleDto {

    private Long id;
    private String code;
}
