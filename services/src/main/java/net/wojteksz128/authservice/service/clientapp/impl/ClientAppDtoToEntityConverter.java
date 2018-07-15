package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.clientapp.ClientAppDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class ClientAppDtoToEntityConverter implements Converter<ClientAppDto, ClientApp> {

    private final ClientAppRepository clientAppRepository;

    @Autowired
    public ClientAppDtoToEntityConverter(ClientAppRepository clientAppRepository) {
        this.clientAppRepository = clientAppRepository;
    }

    @Override
    public ClientApp convert(ClientAppDto clientAppDto) {
        ClientApp clientApp = clientAppRepository.findByClientDetails_ClientId(clientAppDto.getClientDetailsDto().getClientId());
        prepareEntity(clientAppDto, clientApp);
        return clientApp;
    }

    @SuppressWarnings("WeakerAccess")
    void prepareEntity(ClientAppDto clientAppDto, ClientApp clientApp) {
        // TODO: 15.07.2018 Dodaj nazwę clientApp
        clientApp.setClientId(clientAppDto.getClientDetailsDto().getClientId());
        clientApp.setDescription(clientAppDto.getDescription());
    }
}
