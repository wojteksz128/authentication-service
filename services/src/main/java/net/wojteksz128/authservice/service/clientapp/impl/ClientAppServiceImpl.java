package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.clientapp.ClientAppDto;
import net.wojteksz128.authservice.service.clientapp.ClientAppService;
import net.wojteksz128.authservice.service.clientapp.CreateClientAppDto;
import net.wojteksz128.authservice.service.clientapp.UpdateClientAppDto;
import net.wojteksz128.authservice.service.exception.EmptyObjectException;
import net.wojteksz128.authservice.service.exception.InvalidRequestException;
import net.wojteksz128.authservice.service.exception.ObjectNotCorrespondingException;
import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsController;
import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ClientAppServiceImpl implements ClientAppService {

    private final ClientAppController clientAppController;
    private final CreateAppDtoToOAuthClientDetailsDtoConverter createAppDtoToOAuthClientDetailsDtoConverter;
    private final OAuthClientDetailsController clientDetailsController;
    private final CreateAppDtoToClientAppConverter createAppDtoToClientAppConverter;
    private final ClientAppToDtoConverter clientAppToDtoConverter;

    @Autowired
    public ClientAppServiceImpl(ClientAppController clientAppController, CreateAppDtoToOAuthClientDetailsDtoConverter createAppDtoToOAuthClientDetailsDtoConverter, OAuthClientDetailsController clientDetailsController, CreateAppDtoToClientAppConverter createAppDtoToClientAppConverter, ClientAppToDtoConverter clientAppToDtoConverter) {
        this.clientAppController = clientAppController;
        this.createAppDtoToOAuthClientDetailsDtoConverter = createAppDtoToOAuthClientDetailsDtoConverter;
        this.clientDetailsController = clientDetailsController;
        this.createAppDtoToClientAppConverter = createAppDtoToClientAppConverter;
        this.clientAppToDtoConverter = clientAppToDtoConverter;
    }

    @Override
    public ClientAppDto createNew(CreateClientAppDto newApp) throws EmptyObjectException {
        if (newApp == null) {
            throw new EmptyObjectException("Attempt to save a null ClientApp object");
        }

        final OAuthClientDetailsDto newClientDetails = createAppDtoToOAuthClientDetailsDtoConverter.convert(newApp);
        final OAuthClientDetailsDto createdClientDetails = clientDetailsController.createNew(newClientDetails);

        if (createdClientDetails == null) {
            throw new EmptyObjectException("OAuth client details is not created");
        }

        final ClientApp newClientApp = createAppDtoToClientAppConverter.convert(newApp);
        final ClientApp newClientAppEntity = clientAppController.createNew(newClientApp);

        if (newClientAppEntity == null) {
            throw new EmptyObjectException("ClientApp is not created");
        }
        return clientAppToDtoConverter.convert(newClientAppEntity);
    }

    @Override
    public ClientAppDto getAppByClientId(String clientId) {
        // TODO: 21.07.2018 Implement this based on ClientAppController
        return null;
    }

    @Override
    public void updateApp(String clientId, UpdateClientAppDto updatedApp) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException {
        // TODO: 21.07.2018 Implement this based on ClientAppController
    }

    @Override
    public void deleteApp(String clientId, ClientAppDto deletedApp) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException {
        // TODO: 21.07.2018 Implement this based on ClientAppController
    }

    @Override
    public List<ClientAppDto> getAllUserApps(Long userId) {
        // TODO: 21.07.2018 Implement this based on ClientAppController
        return null;
    }
}
