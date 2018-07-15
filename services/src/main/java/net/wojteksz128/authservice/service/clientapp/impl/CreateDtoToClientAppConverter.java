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

@Component
class CreateDtoToClientAppConverter implements Converter<CreateClientAppDto, ClientApp> {

    private final UserService userService;
    private final CreateDtoToOAuthClientDetailsConverter createDtoToOAuthClientDetailsConverter;

    @Autowired
    public CreateDtoToClientAppConverter(UserService userService, CreateDtoToOAuthClientDetailsConverter createDtoToOAuthClientDetailsConverter) {
        this.userService = userService;
        this.createDtoToOAuthClientDetailsConverter = createDtoToOAuthClientDetailsConverter;
    }

    @Override
    public ClientApp convert(CreateClientAppDto createClientAppDto) {
        ClientApp clientApp = new ClientApp();
        prepareEntity(createClientAppDto, clientApp);
        return clientApp;
    }

    private void prepareEntity(CreateClientAppDto createClientAppDto, ClientApp clientApp) {
        final UserDto currentLoggedUser = userService.getCurrentLoggedUser()
            .orElseThrow(() -> new UsernameNotFoundException("Current logged user not found"));

        clientApp.setClientDetails(createDtoToOAuthClientDetailsConverter.convert(createClientAppDto));
        clientApp.setCreateDate(LocalDateTime.now(ZoneId.systemDefault()));
        clientApp.setDescription(createClientAppDto.getDescription());
        clientApp.setUserId(currentLoggedUser.getId());
    }
}
