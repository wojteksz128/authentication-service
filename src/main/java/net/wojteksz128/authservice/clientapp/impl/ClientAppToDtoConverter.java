package net.wojteksz128.authservice.clientapp.impl;

import net.wojteksz128.authservice.clientapp.ClientAppDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class ClientAppToDtoConverter implements Converter<ClientApp, ClientAppDto> {

    @Override
    public ClientAppDto convert(ClientApp clientApp) {
        ClientAppDto clientAppDto = new ClientAppDto();

        clientAppDto.setGuid(clientApp.getGuid());
        clientAppDto.setName(clientApp.getName());
        clientAppDto.setDescription(clientApp.getDescription());
        clientAppDto.setCreateDate(clientApp.getCreateDate());

        return clientAppDto;
    }
}
