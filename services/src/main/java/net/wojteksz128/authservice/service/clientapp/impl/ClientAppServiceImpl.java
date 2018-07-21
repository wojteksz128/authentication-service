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

// TODO: 21.07.2018 Zaimplementuj transakcyjność operacji
@Component
class ClientAppServiceImpl implements ClientAppService {

    private final ClientAppController clientAppController;
    private final CreateAppDtoToOAuthClientDetailsDtoConverter createAppDtoToOAuthClientDetailsDtoConverter;
    private final OAuthClientDetailsController clientDetailsController;
    private final CreateAppDtoToClientAppConverter createAppDtoToClientAppConverter;
    private final ClientAppToDtoConverter clientAppToDtoConverter;
    private final UpdateClientAppDtoToClientAppDtoConverter updateClientAppDtoToClientAppDtoConverter;
    private final ClientAppDtoToEntityConverter clientAppDtoToEntityConverter;

    @Autowired
    public ClientAppServiceImpl(ClientAppController clientAppController, CreateAppDtoToOAuthClientDetailsDtoConverter createAppDtoToOAuthClientDetailsDtoConverter, OAuthClientDetailsController clientDetailsController, CreateAppDtoToClientAppConverter createAppDtoToClientAppConverter, ClientAppToDtoConverter clientAppToDtoConverter, UpdateClientAppDtoToClientAppDtoConverter updateClientAppDtoToClientAppDtoConverter, ClientAppDtoToEntityConverter clientAppDtoToEntityConverter) {
        this.clientAppController = clientAppController;
        this.createAppDtoToOAuthClientDetailsDtoConverter = createAppDtoToOAuthClientDetailsDtoConverter;
        this.clientDetailsController = clientDetailsController;
        this.createAppDtoToClientAppConverter = createAppDtoToClientAppConverter;
        this.clientAppToDtoConverter = clientAppToDtoConverter;
        this.updateClientAppDtoToClientAppDtoConverter = updateClientAppDtoToClientAppDtoConverter;
        this.clientAppDtoToEntityConverter = clientAppDtoToEntityConverter;
    }

    @Override
    public ClientAppDto createNew(CreateClientAppDto newApp) throws EmptyObjectException {
        // TODO: 21.07.2018 Sprawdź, czy użytkownik może dodawać swoje aplikacje klienckie do usługi
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
    public ClientAppDto getAppByClientId(String clientId) throws EmptyObjectException {
        if (clientId == null || clientId.isEmpty()) {
            throw new EmptyObjectException("clientId is Empty");
        }

        final ClientApp appEntity = clientAppController.findByClientId(clientId);
        return clientAppToDtoConverter.convert(appEntity);
    }

    @Override
    public void updateApp(String clientId, UpdateClientAppDto updatedApp) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException {
        // TODO: 21.07.2018 Check user privileges
        if (updatedApp == null) {
            throw new EmptyObjectException("Attempt to use a null object");
        }
        if (clientId == null) {
            throw new InvalidRequestException("clientId is null");
        }
        if (!updatedApp.getClientId().equals(clientId)) {
            throw new ObjectNotCorrespondingException("ClientApp is not requested object.");
        }

        final ClientAppDto clientAppDto = updateClientAppDtoToClientAppDtoConverter.convert(updatedApp);
        clientDetailsController.update(clientId, clientAppDto.getClientDetailsDto());

        final ClientApp clientAppEntity = clientAppDtoToEntityConverter.convert(clientAppDto);
        final ClientApp updatedAppEntity = clientAppController.updateApp(clientAppEntity);
        if (updatedAppEntity == null) {
            throw new EmptyObjectException("ClientApp is not updated");
        }
    }

    @Override
    public void deleteApp(String clientId, ClientAppDto deletedApp) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException {
        // TODO: 21.07.2018 Check user privileges
        if (deletedApp == null) {
            throw new EmptyObjectException("Attempt to use a null object");
        }
        if (clientId == null) {
            throw new InvalidRequestException("clientId is null");
        }
        if (!clientId.equals(deletedApp.getClientDetailsDto().getClientId())) {
            throw new ObjectNotCorrespondingException("ClientApp is not requested object");
        }

        // TODO: 21.07.2018 Sprawdź poprawność wykonania poniższej operacji
        clientDetailsController.delete(clientId, deletedApp.getClientDetailsDto());

        final ClientApp clientAppEntity = clientAppDtoToEntityConverter.convert(deletedApp);
        // TODO: 21.07.2018 Sprawdź poprawność wykonania poniższej operacji
        clientAppController.deleteApp(clientAppEntity);

    }

    @Override
    public List<ClientAppDto> getAllUserApps(Long userId) {
        // TODO: 21.07.2018 Implement this based on ClientAppController
        return null;
    }
}
