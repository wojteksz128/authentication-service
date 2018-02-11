package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.clientapp.ClientAppController;
import net.wojteksz128.authservice.service.clientapp.ClientAppDto;
import net.wojteksz128.authservice.service.clientapp.CreateClientAppDto;
import net.wojteksz128.authservice.service.exception.EmptyObjectException;
import net.wojteksz128.authservice.service.exception.InvalidRequestException;
import net.wojteksz128.authservice.service.exception.ObjectNotCorrespondingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Component
class ClientAppControllerImpl implements ClientAppController {

    private final ClientAppRepository clientAppRepository;
    private final CreateDtoToClientAppConverter createDtoToClientAppConverter;
    private final DtoToClientAppConverter dtoToClientAppConverter;
    private final ClientAppToDtoConverter clientAppToDtoConverter;

    @Autowired
    public ClientAppControllerImpl(ClientAppRepository clientAppRepository, CreateDtoToClientAppConverter createDtoToClientAppConverter, DtoToClientAppConverter dtoToClientAppConverter, ClientAppToDtoConverter clientAppToDtoConverter) {
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
    public ClientAppDto getAppByGuid(String appGuid) {
        return clientAppToDtoConverter.convert(clientAppRepository.findByGuid(appGuid));
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

    private void checkValidity(String appGuid, ClientAppDto app) throws EmptyObjectException, InvalidRequestException, ObjectNotCorrespondingException {
        if (app == null) {
            throw new EmptyObjectException("Attempt to use a null object.");
        }

        if (appGuid == null) {
            throw new InvalidRequestException("App guid is null.");
        }

        if (!appGuid.equals(app.getGuid())) {
            throw new ObjectNotCorrespondingException("Object is not requested object.");
        }

        clientAppRepository.findByGuid(appGuid);
    }
}
