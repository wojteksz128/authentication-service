package net.wojteksz128.authservice.clientapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class DtoToClientAppConverter implements Converter<ClientAppDto, ClientApp> {

    private final ClientAppRepository clientAppRepository;

    @Autowired
    public DtoToClientAppConverter(ClientAppRepository clientAppRepository) {
        this.clientAppRepository = clientAppRepository;
    }

    @Override
    public ClientApp convert(ClientAppDto clientAppDto) {
        ClientApp clientApp = clientAppRepository.findByGuid(clientAppDto.getGuid());

        clientApp.setName(clientAppDto.getName());
        clientApp.setDescription(clientAppDto.getDescription());

        return clientApp;
    }
}
