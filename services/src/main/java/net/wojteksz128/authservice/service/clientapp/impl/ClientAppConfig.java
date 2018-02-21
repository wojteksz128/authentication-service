package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.clientapp.ClientAppController;
import net.wojteksz128.authservice.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ClientAppConfig {

    private final ClientAppRepository clientAppRepository;

    private final UserService userService;

    @Autowired
    public ClientAppConfig(ClientAppRepository clientAppRepository, UserService userService) {
        this.clientAppRepository = clientAppRepository;
        this.userService = userService;
    }

    @Bean
    public ClientAppController clientAppController() {
        return new ClientAppControllerImpl(clientAppRepository, createDtoToClientAppConverter(),
            dtoToClientAppConverter(), clientAppToDtoConverter());
    }

    @Bean
    public CreateDtoToClientAppConverter createDtoToClientAppConverter() {
        return new CreateDtoToClientAppConverter(userService);
    }

    @Bean
    public DtoToClientAppConverter dtoToClientAppConverter() {
        return new DtoToClientAppConverter(clientAppRepository);
    }

    @Bean
    public ClientAppToDtoConverter clientAppToDtoConverter() {
        return new ClientAppToDtoConverter();
    }
}
