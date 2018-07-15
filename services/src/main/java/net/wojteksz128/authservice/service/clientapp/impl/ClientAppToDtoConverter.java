package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.UserDetailsType;
import net.wojteksz128.authservice.service.clientapp.ClientAppDto;
import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class ClientAppToDtoConverter implements Converter<ClientApp, ClientAppDto> {

    private final OAuthClientDetailsController clientDetailsController;

    @Autowired
    public ClientAppToDtoConverter(OAuthClientDetailsController clientDetailsController) {
        this.clientDetailsController = clientDetailsController;
    }

    @Override
    public ClientAppDto convert(ClientApp clientApp) {
        ClientAppDto clientAppDto = new ClientAppDto();
        prepareDto(clientApp, clientAppDto);
        return clientAppDto;
    }

    @SuppressWarnings("WeakerAccess")
    void prepareDto(ClientApp clientApp, ClientAppDto clientAppDto) {
        clientAppDto.setId(clientApp.getId());
        clientAppDto.setClientDetailsDto(clientDetailsController.getByClientId(clientApp.getClientId()));
        clientAppDto.setName(clientApp.getName());
        clientAppDto.setDescription(clientApp.getDescription());
        clientAppDto.setCreateDate(clientApp.getCreateDate());
        clientAppDto.setFullNameRequired(clientAppDto.getClientDetailsDto().getScope().contains(UserDetailsType.FULL_NAME.name()));
        clientAppDto.setBirthDateRequired(clientAppDto.getClientDetailsDto().getScope().contains(UserDetailsType.BIRTH_DATE.name()));
        clientAppDto.setEmailRequired(clientAppDto.getClientDetailsDto().getScope().contains(UserDetailsType.E_MAIL.name()));
        clientAppDto.setContactRequired(clientAppDto.getClientDetailsDto().getScope().contains(UserDetailsType.CONTACT_DATA.name()));
    }
}
