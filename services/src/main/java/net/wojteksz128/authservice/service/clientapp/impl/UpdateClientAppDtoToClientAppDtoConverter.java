package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.UserDetailsType;
import net.wojteksz128.authservice.service.clientapp.ClientAppDto;
import net.wojteksz128.authservice.service.clientapp.ClientAppService;
import net.wojteksz128.authservice.service.clientapp.UpdateClientAppDto;
import net.wojteksz128.authservice.service.exception.EmptyObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class UpdateClientAppDtoToClientAppDtoConverter implements Converter<UpdateClientAppDto, ClientAppDto> {


    private final ClientAppService clientAppService;

    @Autowired
    public UpdateClientAppDtoToClientAppDtoConverter(@Lazy ClientAppService clientAppService) {
        this.clientAppService = clientAppService;
    }

    @Override
    public ClientAppDto convert(UpdateClientAppDto updateClientAppDto) {
        ClientAppDto clientAppDto;
        try {
            clientAppDto = clientAppService.getAppByClientId(updateClientAppDto.getClientId());
        } catch (EmptyObjectException e) {
            return null;
        }

        clientAppDto.setName(updateClientAppDto.getName());
        clientAppDto.getClientDetailsDto().setClientSecret(updateClientAppDto.getClientSecret());
        clientAppDto.getClientDetailsDto().setWebServerRedirectUri(updateClientAppDto.getWebServerRedirectUri());
        clientAppDto.setDescription(updateClientAppDto.getDescription());
        clientAppDto.getClientDetailsDto().setScope(prepareScope(updateClientAppDto));

        return clientAppDto;
    }

    private List<String> prepareScope(UpdateClientAppDto dto) {
        final List<String> scopeList = new ArrayList<>();

        if (dto.getFullNameRequired())
            scopeList.add(UserDetailsType.FULL_NAME.name());
        if (dto.getBirthDateRequired())
            scopeList.add(UserDetailsType.BIRTH_DATE.name());
        if (dto.getEmailRequired())
            scopeList.add(UserDetailsType.E_MAIL.name());
        if (dto.getContactRequired())
            scopeList.add(UserDetailsType.CONTACT_DATA.name());

        return scopeList;
    }
}
