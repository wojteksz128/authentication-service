package net.wojteksz128.authservice.service.oauth.impl;

import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class OAuthClientDetailsToDtoConverter implements Converter<OAuthClientDetails, OAuthClientDetailsDto> {

    @Override
    public OAuthClientDetailsDto convert(OAuthClientDetails clientDetails) {
        final OAuthClientDetailsDto dto = new OAuthClientDetailsDto();

        prepareDto(clientDetails, dto);

        return dto;
    }

    @SuppressWarnings("Duplicates")
    private void prepareDto(OAuthClientDetails clientDetails, OAuthClientDetailsDto dto) {
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
    }
}
