package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.clientapp.ClientAppDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class ClientAppDtoToEntityConverter implements Converter<ClientAppDto, ClientApp> {

    private final ClientAppRepository clientAppRepository;
    private final OAuthClientDetailsDtoToEntityConverter clientDetailsDtoToEntityConverter;

    @Autowired
    public ClientAppDtoToEntityConverter(ClientAppRepository clientAppRepository, OAuthClientDetailsDtoToEntityConverter clientDetailsDtoToEntityConverter) {
        this.clientAppRepository = clientAppRepository;
        this.clientDetailsDtoToEntityConverter = clientDetailsDtoToEntityConverter;
    }

    @Override
    public ClientApp convert(ClientAppDto clientAppDto) {
        ClientApp clientApp = clientAppRepository.findByClientDetails_ClientId(clientAppDto.getClientDetailsDto().getClientId());
        prepareEntity(clientAppDto, clientApp);
        return clientApp;
    }

    @SuppressWarnings("WeakerAccess")
    void prepareEntity(ClientAppDto clientAppDto, ClientApp clientApp) {
        clientApp.setDescription(clientAppDto.getDescription());
        clientDetailsDtoToEntityConverter.prepareEntity(clientAppDto.getClientDetailsDto(), clientApp.getClientDetails());
    }
}
