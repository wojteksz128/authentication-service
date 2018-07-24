package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.clientapp.ClientAppDto;
import net.wojteksz128.authservice.service.exception.EmptyObjectException;
import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class ClientAppToDtoConverter implements Converter<ClientApp, ClientAppDto> {

    private final OAuthClientDetailsService clientDetailsService;

    @Autowired
    public ClientAppToDtoConverter(OAuthClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    @Override
    public ClientAppDto convert(ClientApp clientApp) {
        ClientAppDto clientAppDto = new ClientAppDto();
        prepareDto(clientApp, clientAppDto);
        return clientAppDto;
    }

    @SuppressWarnings("WeakerAccess")
    void prepareDto(ClientApp clientApp, ClientAppDto clientAppDto) {
        clientAppDto.setId(clientApp.getId());
        try {
            clientAppDto.setClientDetailsDto(clientDetailsService.getByClientId(clientApp.getClientId()));
        } catch (EmptyObjectException e) {
            e.printStackTrace();
        }
        clientAppDto.setName(clientApp.getName());
        clientAppDto.setDescription(clientApp.getDescription());
        clientAppDto.setCreateDate(clientApp.getCreateDate());
    }
}
