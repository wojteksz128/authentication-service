package net.wojteksz128.authservice.clientapp;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class CreateDtoToClientAppConverter implements Converter<CreateClientAppDto, ClientApp> {

    @Override
    public ClientApp convert(CreateClientAppDto createClientAppDto) {
        ClientApp clientApp = new ClientApp();

        clientApp.setName(createClientAppDto.getName());
        clientApp.setDescription(createClientAppDto.getDescription());

        return clientApp;
    }
}
