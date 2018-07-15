package net.wojteksz128.authservice.service.user.impl;

import net.wojteksz128.authservice.service.user.UserPersonalDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class UserPersonalDataDtoToEntityConverter implements Converter<UserPersonalDataDto, UserPersonalData> {


    private final UserPersonalDataRepository userPersonalDataRepository;

    @Autowired
    UserPersonalDataDtoToEntityConverter(UserPersonalDataRepository userPersonalDataRepository) {
        this.userPersonalDataRepository = userPersonalDataRepository;
    }

    @Override
    public UserPersonalData convert(UserPersonalDataDto dto) {
        final Optional<UserPersonalData> userPersonalData = prepareUserPersonalData(dto);

        userPersonalData.ifPresent(personalData -> {
            personalData.setBirthDate(dto.getBirthDate());
            personalData.setEmail(dto.getEmail());
            personalData.setFirstName(dto.getFirstName());
            personalData.setLastName(dto.getLastName());
            personalData.setPhoneNumber(dto.getPhoneNumber());
            personalData.setUrl(dto.getUrl());
        });

        return userPersonalData.orElse(null);
    }

    private Optional<UserPersonalData> prepareUserPersonalData(UserPersonalDataDto source) {
        Optional<UserPersonalData> userPersonalData;
        if (source.getUser() == null) {
            userPersonalData = Optional.of(new UserPersonalData());
        } else {
            userPersonalData = userPersonalDataRepository.findByUserLogin(source.getUser().getLogin());
        }

        return userPersonalData;
    }
}
