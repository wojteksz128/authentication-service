package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.UserDetailsType;
import net.wojteksz128.authservice.service.clientapp.CreateClientAppDto;
import net.wojteksz128.authservice.service.oauth.OAuthClientDefaultConfig;
import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class CreateAppDtoToOAuthClientDetailsDtoConverter implements Converter<CreateClientAppDto, OAuthClientDetailsDto> {

    private final OAuthClientDefaultConfig clientDefaultConfig;

    @Autowired
    CreateAppDtoToOAuthClientDetailsDtoConverter(OAuthClientDefaultConfig clientDefaultConfig) {
        this.clientDefaultConfig = clientDefaultConfig;
    }

    @Override
    public OAuthClientDetailsDto convert(CreateClientAppDto createClientAppDto) {
        final OAuthClientDetailsDto clientDetailsDto = new OAuthClientDetailsDto();

        clientDetailsDto.setClientId(createClientAppDto.getClientId());
        clientDetailsDto.setClientSecret(createClientAppDto.getClientSecret());
        clientDetailsDto.setScope(prepareScope(createClientAppDto));
        clientDetailsDto.setAuthorizedGrantTypes(clientDefaultConfig.getAuthorizedGrantTypes());
        clientDetailsDto.setWebServerRedirectUri(createClientAppDto.getWebServerRedirectUri());
        clientDetailsDto.setAccessTokenValidity(clientDefaultConfig.getAccessTokenValidity());
        clientDetailsDto.setRefreshTokenValidity(clientDefaultConfig.getRefreshTokenValidity());
        clientDetailsDto.setAutoApprove(clientDefaultConfig.getAutoApprove());

        return clientDetailsDto;
    }

    private List<String> prepareScope(CreateClientAppDto dto) {
        final List<String> scopeList = new ArrayList<>();

        if (dto.isFullNameRequired())
            scopeList.add(UserDetailsType.FULL_NAME.name());
        if (dto.isBirthDateRequired())
            scopeList.add(UserDetailsType.BIRTH_DATE.name());
        if (dto.isEmailRequired())
            scopeList.add(UserDetailsType.E_MAIL.name());
        if (dto.isContactRequired())
            scopeList.add(UserDetailsType.CONTACT_DATA.name());

        return scopeList;
    }
}
