package net.wojteksz128.authservice.service.user.impl;

import net.wojteksz128.authservice.service.user.RoleDto;
import net.wojteksz128.authservice.service.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
class UserToDtoConverter implements Converter<User, UserDto> {

    private final RoleToDtoConverter roleToDtoConverter;
    private final UserPersonalDataToDtoConverter userPersonalDataToDtoConverter;

    @Autowired
    public UserToDtoConverter(RoleToDtoConverter roleToDtoConverter, UserPersonalDataToDtoConverter userPersonalDataToDtoConverter) {
        this.roleToDtoConverter = roleToDtoConverter;
        this.userPersonalDataToDtoConverter = userPersonalDataToDtoConverter;
    }

    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        List<RoleDto> userRoles = user.getRoles().stream().map(roleToDtoConverter::convert).collect(Collectors.toList());

        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setRoles(new HashSet<>(userRoles));
        userDto.setPersonalData(userPersonalDataToDtoConverter.convert(user.getPersonalData()));

        return userDto;
    }
}
