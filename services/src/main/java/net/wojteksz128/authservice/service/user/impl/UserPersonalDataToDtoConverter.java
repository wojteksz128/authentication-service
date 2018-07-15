package net.wojteksz128.authservice.service.user.impl;

import net.wojteksz128.authservice.service.user.UserPersonalDataDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class UserPersonalDataToDtoConverter implements Converter<UserPersonalData, UserPersonalDataDto> {

    @Override
    public UserPersonalDataDto convert(UserPersonalData source) {
        final UserPersonalDataDto dto = new UserPersonalDataDto();

        dto.setBirthDate(source.getBirthDate());
        dto.setEmail(source.getEmail());
        dto.setFirstName(source.getFirstName());
        dto.setLastName(source.getLastName());
        dto.setPhoneNumber(source.getPhoneNumber());
        dto.setUrl(source.getUrl());

        return dto;
    }
}
