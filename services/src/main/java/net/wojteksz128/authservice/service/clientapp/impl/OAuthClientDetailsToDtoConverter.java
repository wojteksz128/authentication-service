package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.clientapp.OAuthClientDetailsDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class OAuthClientDetailsToDtoConverter implements Converter<OAuthClientDetails, OAuthClientDetailsDto> {

    @Override
    public OAuthClientDetailsDto convert(OAuthClientDetails clientDetails) {
        final OAuthClientDetailsDto dto = new OAuthClientDetailsDto();

        dto.setClientId(clientDetails.getClientId());
        dto.setResourceIds(clientDetails.getResourceIds());
        dto.setClientSecret(clientDetails.getClientSecret());
        dto.setScope(clientDetails.getScope());
        dto.setAuthorizedGrantTypes(clientDetails.getAuthorizedGrantTypes());
        dto.setWebServerRedirectUri(clientDetails.getWebServerRedirectUri());
        dto.setAuthorities(clientDetails.getAuthorities());
        dto.setAccessTokenValidity(clientDetails.getAccessTokenValidity());
        dto.setAdditionalInformation(clientDetails.getAdditionalInformation());
        dto.setAutoApprove(clientDetails.getAutoApprove());

        return dto;
    }
}
