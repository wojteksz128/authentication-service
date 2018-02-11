package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.clientapp.CreateClientAppDto;
import net.wojteksz128.authservice.service.user.impl.UserDetailsImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
        clientApp.setCreateDate(LocalDateTime.now(ZoneId.systemDefault()));
        clientApp.setUserId(user.getId());

        return clientApp;
    }
}
