package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.clientapp.OAuthClientDetailsDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class OAuthClientDetailsDtoToEntityConverter implements Converter<OAuthClientDetailsDto, OAuthClientDetails> {

    @Override
    public OAuthClientDetails convert(OAuthClientDetailsDto dto) {
        final OAuthClientDetails clientDetails = new OAuthClientDetails();

        clientDetails.setClientId(dto.getClientId());
        clientDetails.setResourceIds(dto.getResourceIds());
        clientDetails.setClientSecret(dto.getClientSecret());
        clientDetails.setScope(dto.getScope());
        clientDetails.setAuthorizedGrantTypes(dto.getAuthorizedGrantTypes());
        clientDetails.setWebServerRedirectUri(dto.getWebServerRedirectUri());
        clientDetails.setAuthorities(dto.getAuthorities());
        clientDetails.setAccessTokenValidity(dto.getAccessTokenValidity());
        clientDetails.setAdditionalInformation(dto.getAdditionalInformation());
        clientDetails.setAutoApprove(dto.getAutoApprove());

        return clientDetails;
    }
}
