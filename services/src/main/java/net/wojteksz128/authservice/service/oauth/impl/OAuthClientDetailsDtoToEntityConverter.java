package net.wojteksz128.authservice.service.oauth.impl;

import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class OAuthClientDetailsDtoToEntityConverter implements Converter<OAuthClientDetailsDto, OAuthClientDetails> {

    @Override
    public OAuthClientDetails convert(OAuthClientDetailsDto dto) {
        final OAuthClientDetails clientDetails = new OAuthClientDetails();
        prepareEntity(dto, clientDetails);
        return clientDetails;
    }

    @SuppressWarnings("Duplicates")
    private void prepareEntity(OAuthClientDetailsDto clientDetailsDto, OAuthClientDetails clientDetails) {
        clientDetails.setClientId(clientDetailsDto.getClientId());
        clientDetails.setResourceIds(clientDetailsDto.getResourceIds());
        clientDetails.setClientSecret(clientDetailsDto.getClientSecret());
        clientDetails.setScope(clientDetailsDto.getScope());
        clientDetails.setAuthorizedGrantTypes(clientDetailsDto.getAuthorizedGrantTypes());
        clientDetails.setWebServerRedirectUri(clientDetailsDto.getWebServerRedirectUri());
        clientDetails.setAuthorities(clientDetailsDto.getAuthorities());
        clientDetails.setAccessTokenValidity(clientDetailsDto.getAccessTokenValidity());
        clientDetails.setRefreshTokenValidity(clientDetailsDto.getRefreshTokenValidity());
        clientDetails.setAdditionalInformation(clientDetailsDto.getAdditionalInformation());
        clientDetails.setAutoApprove(clientDetailsDto.getAutoApprove());
    }
}
