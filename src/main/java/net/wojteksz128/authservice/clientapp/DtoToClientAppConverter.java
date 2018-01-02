package net.wojteksz128.authservice.clientapp;

import net.wojteksz128.authservice.exception.InvalidRequestException;
import net.wojteksz128.authservice.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class DtoToClientAppConverter implements Converter<ClientAppDto, ClientApp> {

    @Autowired
    private ClientAppRepository clientAppRepository;

    @Override
    public ClientApp convert(ClientAppDto clientAppDto) {
        ClientApp clientApp = new ClientApp();


        try {
            clientApp.setId(clientAppRepository.findByGuid(clientAppDto.getGuid()).getId());
        } catch (InvalidRequestException | ObjectNotFoundException e) {
            clientApp.setId(null);
        }
        clientApp.setGuid(clientAppDto.getGuid());
        clientApp.setName(clientAppDto.getName());
        clientApp.setDescription(clientAppDto.getDescription());

        return clientApp;
    }
}
