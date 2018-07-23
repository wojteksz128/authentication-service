package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.clientapp.ClientAppDto;
import net.wojteksz128.authservice.service.clientapp.ClientAppService;
import net.wojteksz128.authservice.service.clientapp.CreateClientAppDto;
import net.wojteksz128.authservice.service.clientapp.UpdateClientAppDto;
import net.wojteksz128.authservice.service.exception.EmptyObjectException;
import net.wojteksz128.authservice.service.exception.InvalidRequestException;
import net.wojteksz128.authservice.service.exception.ObjectNotCorrespondingException;
import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsDto;
import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

// TODO: 21.07.2018 Zaimplementuj transakcyjność operacji
@Component
class ClientAppServiceImpl implements ClientAppService {

    private final ClientAppController clientAppController;
    private final CreateAppDtoToOAuthClientDetailsDtoConverter createAppDtoToOAuthClientDetailsDtoConverter;
    private final OAuthClientDetailsService clientDetailsService;
    private final CreateAppDtoToClientAppConverter createAppDtoToClientAppConverter;
    private final ClientAppToDtoConverter clientAppToDtoConverter;
    private final UpdateClientAppDtoToClientAppDtoConverter updateClientAppDtoToClientAppDtoConverter;
    private final ClientAppDtoToEntityConverter clientAppDtoToEntityConverter;

    @Autowired
    public ClientAppServiceImpl(ClientAppController clientAppController, CreateAppDtoToOAuthClientDetailsDtoConverter createAppDtoToOAuthClientDetailsDtoConverter, OAuthClientDetailsService clientDetailsService, CreateAppDtoToClientAppConverter createAppDtoToClientAppConverter, ClientAppToDtoConverter clientAppToDtoConverter, UpdateClientAppDtoToClientAppDtoConverter updateClientAppDtoToClientAppDtoConverter, ClientAppDtoToEntityConverter clientAppDtoToEntityConverter) {
        this.clientAppController = clientAppController;
        this.createAppDtoToOAuthClientDetailsDtoConverter = createAppDtoToOAuthClientDetailsDtoConverter;
        this.clientDetailsService = clientDetailsService;
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
        final OAuthClientDetailsDto createdClientDetails = clientDetailsService.createNew(newClientDetails);

        if (createdClientDetails == null) {
            // TODO: 23.07.2018 Zmień wyjątek na odpowiedniejszy
            throw new EmptyObjectException("OAuth client details is not created");
        }

        final ClientApp newClientApp = createAppDtoToClientAppConverter.convert(newApp);
        final ClientApp newClientAppEntity = clientAppController.createNew(newClientApp);

        if (newClientAppEntity == null) {
            // TODO: 23.07.2018 Zmień wyjątek na odpowiedniejszy
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
        clientDetailsService.update(clientId, clientAppDto.getClientDetailsDto());

        final ClientApp clientAppEntity = clientAppDtoToEntityConverter.convert(clientAppDto);
        final ClientApp updatedAppEntity = clientAppController.updateApp(clientAppEntity);
        if (updatedAppEntity == null) {
            // TODO: 23.07.2018 Zmień wyjątek na odpowiedniejszy
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
        clientDetailsService.delete(clientId, deletedApp.getClientDetailsDto());

        final ClientApp clientAppEntity = clientAppDtoToEntityConverter.convert(deletedApp);
        // TODO: 21.07.2018 Sprawdź poprawność wykonania poniższej operacji
        clientAppController.deleteApp(clientAppEntity);

    }

    @Override
    public Collection<ClientAppDto> getAllUserApps(Long userId) {
        // TODO: 21.07.2018 Check user privileges
        final Collection<ClientApp> allUserApps = clientAppController.getAllUserApps(userId);
        return allUserApps
            .stream()
            .map(clientAppToDtoConverter::convert)
            .collect(Collectors.toList());
    }
}
