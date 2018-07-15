package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.clientapp.ClientAppDto;
import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class ClientAppToDtoConverter implements Converter<ClientApp, ClientAppDto> {

    private final OAuthClientDetailsController clientDetailsController;

    @Autowired
    public ClientAppToDtoConverter(OAuthClientDetailsController clientDetailsController) {
        this.clientDetailsController = clientDetailsController;
    }

    @Override
    public ClientAppDto convert(ClientApp clientApp) {
        ClientAppDto clientAppDto = new ClientAppDto();
        prepareDto(clientApp, clientAppDto);
        return clientAppDto;
    }

    @SuppressWarnings("WeakerAccess")
    void prepareDto(ClientApp clientApp, ClientAppDto clientAppDto) {
        clientAppDto.setClientDetailsDto(clientDetailsController.getByClientId(clientApp.getClientId()));
        clientAppDto.setName(clientApp.getName());
        clientAppDto.setCreateDate(clientApp.getCreateDate());
        clientAppDto.setDescription(clientApp.getDescription());
        clientAppDto.setId(clientApp.getId());
    }
}
