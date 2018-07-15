package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.clientapp.ClientAppController;
import net.wojteksz128.authservice.service.clientapp.ClientAppDto;
import net.wojteksz128.authservice.service.clientapp.UpdateClientAppDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class UpdateClientAppDtoToClientAppDtoConverter implements Converter<UpdateClientAppDto, ClientAppDto> {


    private final ClientAppController clientAppController;

    @Autowired
    public UpdateClientAppDtoToClientAppDtoConverter(@Lazy ClientAppController clientAppController) {
        this.clientAppController = clientAppController;
    }

    @Override
    public ClientAppDto convert(UpdateClientAppDto updateClientAppDto) {
        ClientAppDto clientAppDto = clientAppController.getAppByClientId(updateClientAppDto.getClientId());

        clientAppDto.setName(updateClientAppDto.getName());
        clientAppDto.getClientDetailsDto().setClientSecret(updateClientAppDto.getClientSecret());
        clientAppDto.getClientDetailsDto().setWebServerRedirectUri(updateClientAppDto.getWebServerRedirectUri());
        clientAppDto.setDescription(updateClientAppDto.getDescription());
        clientAppDto.setFullNameRequired(updateClientAppDto.getFullNameRequired());
        clientAppDto.setBirthDateRequired(updateClientAppDto.getBirthDateRequired());
        clientAppDto.setEmailRequired(updateClientAppDto.getEmailRequired());
        clientAppDto.setContactRequired(updateClientAppDto.getContactRequired());

        return clientAppDto;
    }
}
