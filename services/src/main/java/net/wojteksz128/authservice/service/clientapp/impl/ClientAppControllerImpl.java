package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.clientapp.ClientAppController;
import net.wojteksz128.authservice.service.clientapp.ClientAppDto;
import net.wojteksz128.authservice.service.clientapp.CreateClientAppDto;
import net.wojteksz128.authservice.service.exception.EmptyObjectException;
import net.wojteksz128.authservice.service.exception.InvalidRequestException;
import net.wojteksz128.authservice.service.exception.ObjectNotCorrespondingException;
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
    private final CreateDtoToClientAppConverter createDtoToClientAppConverter;
    private final ClientAppDtoToEntityConverter dtoToClientAppConverter;
    private final ClientAppToDtoConverter clientAppToDtoConverter;

    @Autowired
    public ClientAppControllerImpl(ClientAppRepository clientAppRepository, CreateDtoToClientAppConverter createDtoToClientAppConverter, ClientAppDtoToEntityConverter dtoToClientAppConverter, ClientAppToDtoConverter clientAppToDtoConverter) {
        this.clientAppRepository = clientAppRepository;
        this.createDtoToClientAppConverter = createDtoToClientAppConverter;
        this.dtoToClientAppConverter = dtoToClientAppConverter;
        this.clientAppToDtoConverter = clientAppToDtoConverter;
    }

    @Override
    public ClientAppDto createNew(CreateClientAppDto app) throws EmptyObjectException {
        if (app == null) {
            throw new EmptyObjectException("Attempt to save a null object.");
        }

        return clientAppToDtoConverter.convert(clientAppRepository.save(createDtoToClientAppConverter.convert(app)));
    }

    @Override
    public ClientAppDto getAppByClientId(String clientId) {
        return clientAppToDtoConverter.convert(clientAppRepository.findByClientDetails_ClientId(clientId));
    }

    @Override
    public void updateApp(String appGuid, ClientAppDto app) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException {
        checkValidity(appGuid, app);
        clientAppRepository.save(dtoToClientAppConverter.convert(app));
    }

    @Override
    public void deleteApp(String appGuid, ClientAppDto app) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException {
        checkValidity(appGuid, app);
        clientAppRepository.delete(dtoToClientAppConverter.convert(app));
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
     * @param dto information about client app
     * @throws EmptyObjectException client app information is null
     * @throws InvalidRequestException client app identifier is null
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
            throw new ObjectNotCorrespondingException("Object is not requested object.");
        }

        clientAppRepository.findByClientDetails_ClientId(clientId);
    }
}
