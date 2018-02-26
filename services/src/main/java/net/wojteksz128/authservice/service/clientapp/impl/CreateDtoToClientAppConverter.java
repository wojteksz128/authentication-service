package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.clientapp.CreateClientAppDto;
import net.wojteksz128.authservice.service.user.UserDto;
import net.wojteksz128.authservice.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Component
class CreateDtoToClientAppConverter implements Converter<CreateClientAppDto, ClientApp> {

    private final UserService userService;

    @Autowired
    public CreateDtoToClientAppConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ClientApp convert(CreateClientAppDto createClientAppDto) {
        ClientApp clientApp = new ClientApp();
        final UserDto currentLoggedUser = userService.getCurrentLoggedUser()
            .orElseThrow(() -> new UsernameNotFoundException("Current logged user not found"));

        clientApp.setGuid(UUID.randomUUID().toString());
        clientApp.setName(createClientAppDto.getName());
        clientApp.setDescription(createClientAppDto.getDescription());
        clientApp.setCreateDate(LocalDateTime.now(ZoneId.systemDefault()));
        clientApp.setUserId(currentLoggedUser.getId());

        return clientApp;
    }
}
