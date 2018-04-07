package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.clientapp.ClientAppDto;
import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class ClientAppToDtoConverter implements Converter<ClientApp, ClientAppDto> {

    @Autowired
    private OAuthClientDetailsController clientDetailsController;

    @Override
    public ClientAppDto convert(ClientApp clientApp) {
        ClientAppDto clientAppDto = new ClientAppDto();

        clientAppDto.setClientDetailsDto(clientDetailsController.getByClientId(clientApp.getClientId()));
        clientAppDto.setCreateDate(clientApp.getCreateDate());
        clientAppDto.setDescription(clientApp.getDescription());
        clientAppDto.setId(clientApp.getId());

        return clientAppDto;
    }
}
