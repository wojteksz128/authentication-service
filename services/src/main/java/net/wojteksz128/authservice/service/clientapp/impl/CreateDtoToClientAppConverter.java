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
    private final OAuthClientDetailsDtoToEntityConverter clientDetailsDtoToEntityConverter;

    @Autowired
    public CreateDtoToClientAppConverter(UserService userService, OAuthClientDetailsDtoToEntityConverter clientDetailsDtoToEntityConverter) {
        this.userService = userService;
        this.clientDetailsDtoToEntityConverter = clientDetailsDtoToEntityConverter;
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

        // TODO: 15.07.2018 Zmień przygotowywanie encji
//        clientApp.setClientDetails(clientDetailsDtoToEntityConverter.convert(createClientAppDto.getClientDetailsDto()));
        clientApp.setCreateDate(LocalDateTime.now(ZoneId.systemDefault()));
        clientApp.setDescription(createClientAppDto.getDescription());
        clientApp.setUserId(currentLoggedUser.getId());
    }
}
