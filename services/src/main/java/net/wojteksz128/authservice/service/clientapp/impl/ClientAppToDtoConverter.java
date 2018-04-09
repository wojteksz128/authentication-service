package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.clientapp.ClientAppDto;
import net.wojteksz128.authservice.service.clientapp.OAuthClientDetailsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class ClientAppToDtoConverter implements Converter<ClientApp, ClientAppDto> {

    @Autowired
    private OAuthClientDetailsController clientDetailsController;

    @Autowired
    private OAuthClientDetailsToDtoConverter clientDetailsToDtoConverter;

    @Override
    public ClientAppDto convert(ClientApp clientApp) {
        ClientAppDto clientAppDto = new ClientAppDto();

        clientAppDto.setClientDetailsDto(clientDetailsToDtoConverter.convert(clientApp.getClientDetails()));
        clientAppDto.setCreateDate(clientApp.getCreateDate());
        clientAppDto.setDescription(clientApp.getDescription());
        clientAppDto.setId(clientApp.getId());

        return clientAppDto;
    }
}
