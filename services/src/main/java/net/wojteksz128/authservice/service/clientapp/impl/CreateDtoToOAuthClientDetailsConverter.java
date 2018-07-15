package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.UserDetailsType;
import net.wojteksz128.authservice.service.clientapp.CreateClientAppDto;
import net.wojteksz128.authservice.service.oauth.OAuthClientDefaultConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class CreateDtoToOAuthClientDetailsConverter implements Converter<CreateClientAppDto, OAuthClientDetails> {

    private final OAuthClientDefaultConfig clientDefaultConfig;

    @Autowired
    CreateDtoToOAuthClientDetailsConverter(OAuthClientDefaultConfig clientDefaultConfig) {
        this.clientDefaultConfig = clientDefaultConfig;
    }

    @Override
    public OAuthClientDetails convert(CreateClientAppDto dto) {
        final OAuthClientDetails clientDetails = new OAuthClientDetails();

        clientDetails.setClientId(dto.getClientId());
        clientDetails.setClientSecret(dto.getClientSecret());
        clientDetails.setScope(prepareScope(dto));
        clientDetails.setAuthorizedGrantTypes(clientDefaultConfig.getAuthorizedGrantTypes());
        clientDetails.setWebServerRedirectUri(dto.getWebServerRedirectUri());
        clientDetails.setAccessTokenValidity(clientDefaultConfig.getAccessTokenValidity());
        clientDetails.setRefreshTokenValidity(clientDefaultConfig.getRefreshTokenValidity());
        clientDetails.setAutoApprove(clientDefaultConfig.getAutoApprove());

        return clientDetails;
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
