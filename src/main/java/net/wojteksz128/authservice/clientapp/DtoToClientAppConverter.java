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
        ClientApp clientApp;

        try {
            clientApp = clientAppRepository.findByGuid(clientAppDto.getGuid());
        } catch (InvalidRequestException | ObjectNotFoundException e) {
            clientApp = new ClientApp();
            clientApp.setId(null);
        }
        clientApp.setName(clientAppDto.getName());
        clientApp.setDescription(clientAppDto.getDescription());

        return clientApp;
    }
}
