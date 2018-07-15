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
        ClientApp clientApp = clientAppRepository.findByClientId(clientAppDto.getClientDetailsDto().getClientId());
        prepareEntity(clientAppDto, clientApp);
        return clientApp;
    }

    @SuppressWarnings("WeakerAccess")
    void prepareEntity(ClientAppDto clientAppDto, ClientApp clientApp) {
        clientApp.setName(clientAppDto.getName());
        clientApp.setClientId(clientAppDto.getClientDetailsDto().getClientId());
        clientApp.setDescription(clientAppDto.getDescription());
    }
}
