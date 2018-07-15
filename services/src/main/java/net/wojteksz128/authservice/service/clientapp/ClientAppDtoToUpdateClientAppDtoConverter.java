package net.wojteksz128.authservice.service.clientapp;

import net.wojteksz128.authservice.service.UserDetailsType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClientAppDtoToUpdateClientAppDtoConverter implements Converter<ClientAppDto, UpdateClientAppDto> {

    @Override
    public UpdateClientAppDto convert(ClientAppDto clientAppDto) {
        final UpdateClientAppDto updateClientAppDto = new UpdateClientAppDto();

        updateClientAppDto.setName(clientAppDto.getName());
        updateClientAppDto.setCreateDate(clientAppDto.getCreateDate());
        updateClientAppDto.setClientId(clientAppDto.getClientDetailsDto().getClientId());
        updateClientAppDto.setClientSecret(clientAppDto.getClientDetailsDto().getClientSecret());
        updateClientAppDto.setWebServerRedirectUri(clientAppDto.getClientDetailsDto().getWebServerRedirectUri());
        updateClientAppDto.setDescription(clientAppDto.getDescription());
        updateClientAppDto.setFullNameRequired(clientAppDto.getClientDetailsDto().getScope().contains(UserDetailsType.FULL_NAME.name()));
        updateClientAppDto.setBirthDateRequired(clientAppDto.getClientDetailsDto().getScope().contains(UserDetailsType.BIRTH_DATE.name()));
        updateClientAppDto.setEmailRequired(clientAppDto.getClientDetailsDto().getScope().contains(UserDetailsType.E_MAIL.name()));
        updateClientAppDto.setContactRequired(clientAppDto.getClientDetailsDto().getScope().contains(UserDetailsType.CONTACT_DATA.name()));

        return updateClientAppDto;
    }
}
