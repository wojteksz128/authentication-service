package net.wojteksz128.authservice.clientapp;

import net.wojteksz128.authservice.exception.EmptyObjectException;
import net.wojteksz128.authservice.exception.InvalidRequestException;
import net.wojteksz128.authservice.exception.ObjectNotCorrespondingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Component
public class ClientAppController {

    private final ClientAppRepository clientAppRepository;

    private final CreateDtoToClientAppConverter createDtoToClientAppConverter;

    private final DtoToClientAppConverter dtoToClientAppConverter;

    private final ClientAppToDtoConverter clientAppToDtoConverter;

    @Autowired
    public ClientAppController(ClientAppRepository clientAppRepository, CreateDtoToClientAppConverter createDtoToClientAppConverter, DtoToClientAppConverter dtoToClientAppConverter, ClientAppToDtoConverter clientAppToDtoConverter) {
        this.clientAppRepository = clientAppRepository;
        this.createDtoToClientAppConverter = createDtoToClientAppConverter;
        this.dtoToClientAppConverter = dtoToClientAppConverter;
        this.clientAppToDtoConverter = clientAppToDtoConverter;
    }

    public ClientAppDto createNew(CreateClientAppDto app) throws EmptyObjectException {
        if (app == null) {
            throw new EmptyObjectException("Attempt to save a null object.");
        }

        return clientAppToDtoConverter.convert(clientAppRepository.save(createDtoToClientAppConverter.convert(app)));
    }

    public ClientAppDto getAppByGuid(String appGuid) {
        return clientAppToDtoConverter.convert(clientAppRepository.findByGuid(appGuid));
    }

    public void updateApp(String appGuid, ClientAppDto app) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException {
        checkValidity(appGuid, app);
        clientAppRepository.save(dtoToClientAppConverter.convert(app));
    }

    public void deleteApp(String appGuid, ClientAppDto app) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException {
        checkValidity(appGuid, app);
        clientAppRepository.delete(dtoToClientAppConverter.convert(app));
    }

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
