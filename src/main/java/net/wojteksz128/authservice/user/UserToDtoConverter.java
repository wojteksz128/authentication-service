package net.wojteksz128.authservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
class UserToDtoConverter implements Converter<User, UserDto> {

    @Autowired
    private RoleToDtoConverter roleToDtoConverter;

    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        List<RoleDto> userRoles = user.getRoles().stream().map(roleToDtoConverter::convert).collect(Collectors.toList());

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(new HashSet<>(userRoles));

        return userDto;
    }
}
