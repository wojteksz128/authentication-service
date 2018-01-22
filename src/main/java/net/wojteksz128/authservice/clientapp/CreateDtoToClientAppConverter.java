package net.wojteksz128.authservice.clientapp;

import net.wojteksz128.authservice.user.UserDetailsImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class CreateDtoToClientAppConverter implements Converter<CreateClientAppDto, ClientApp> {

    @Override
    public ClientApp convert(CreateClientAppDto createClientAppDto) {
        ClientApp clientApp = new ClientApp();
        final UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        clientApp.setGuid(UUID.randomUUID().toString());
        clientApp.setName(createClientAppDto.getName());
        clientApp.setDescription(createClientAppDto.getDescription());
        clientApp.setUserId(user.getId());

        return clientApp;
    }
}
