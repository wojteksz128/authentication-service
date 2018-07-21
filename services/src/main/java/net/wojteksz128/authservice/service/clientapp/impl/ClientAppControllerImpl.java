package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.clientapp.ClientAppDto;
import net.wojteksz128.authservice.service.exception.EmptyObjectException;
import net.wojteksz128.authservice.service.exception.InvalidRequestException;
import net.wojteksz128.authservice.service.exception.ObjectNotCorrespondingException;
import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Component
@EnableJpaRepositories(basePackageClasses = {ClientAppRepository.class})
class ClientAppControllerImpl implements ClientAppController {

    private final ClientAppRepository clientAppRepository;
    private final ClientAppDtoToEntityConverter dtoToClientAppConverter;
    private final ClientAppToDtoConverter clientAppToDtoConverter;
    private final OAuthClientDetailsController clientDetailsController;

    @Autowired
    public ClientAppControllerImpl(ClientAppRepository clientAppRepository, CreateAppDtoToClientAppConverter createAppDtoToClientAppConverter, ClientAppDtoToEntityConverter dtoToClientAppConverter, ClientAppToDtoConverter clientAppToDtoConverter, CreateAppDtoToOAuthClientDetailsDtoConverter createAppDtoToOAuthClientDetailsDtoConverter, OAuthClientDetailsController clientDetailsController, UpdateClientAppDtoToClientAppDtoConverter updateClientAppDtoToClientAppDtoConverter) {
        this.clientAppRepository = clientAppRepository;
        this.dtoToClientAppConverter = dtoToClientAppConverter;
        this.clientAppToDtoConverter = clientAppToDtoConverter;
        this.clientDetailsController = clientDetailsController;
    }

    @Override
    public ClientApp createNew(ClientApp app) {
        return clientAppRepository.save(app);
    }

    @Override
    public ClientApp findByClientId(String clientId) {
        return clientAppRepository.findByClientId(clientId);
    }

    @Override
    public ClientApp updateApp(ClientApp updatedApp) {
        return clientAppRepository.save(updatedApp);
    }

    @Override
    public void deleteApp(String appGuid, ClientAppDto app) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException {
        checkValidity(appGuid, app);
        clientAppRepository.delete(dtoToClientAppConverter.convert(app));
        clientDetailsController.delete(appGuid, clientDetailsController.getByClientId(app.getClientDetailsDto().getClientId()));
    }

    @Override
    public List<ClientAppDto> getAllUserApps(Long userId) {
        return clientAppRepository.findAllByUserId(userId)
            .stream()
            .map(clientAppToDtoConverter::convert)
            .collect(Collectors.toList());
    }

    /**
     * Throw exception, when request data aren't correct.
     *
     * @param clientId requested client app identifier
     * @param dto      information about client app
     * @throws EmptyObjectException            client app information is null
     * @throws InvalidRequestException         client app identifier is null
     * @throws ObjectNotCorrespondingException clientId in dto and request aren't the same
     */
    private void checkValidity(String clientId, ClientAppDto dto) throws EmptyObjectException, InvalidRequestException, ObjectNotCorrespondingException {
        if (dto == null) {
            throw new EmptyObjectException("Attempt to use a null object.");
        }

        if (clientId == null) {
            throw new InvalidRequestException("");
        }

        if (!clientId.equals(dto.getClientDetailsDto().getClientId())) {
            throw new ObjectNotCorrespondingException("App is not requested object.");
        }

        clientAppRepository.findByClientId(clientId);
    }
}
