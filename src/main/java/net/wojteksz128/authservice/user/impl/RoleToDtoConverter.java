package net.wojteksz128.authservice.user.impl;

import net.wojteksz128.authservice.user.RoleDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class RoleToDtoConverter implements Converter<Role, RoleDto> {

    @Override
    public RoleDto convert(Role role) {
        RoleDto roleDto = new RoleDto();

        roleDto.setId(role.getId());
        roleDto.setCode(role.getCode());

        return roleDto;
    }
}
