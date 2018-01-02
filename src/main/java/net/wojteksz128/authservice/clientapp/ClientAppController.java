package net.wojteksz128.authservice.clientapp;

import net.wojteksz128.authservice.exception.EmptyObjectException;
import net.wojteksz128.authservice.exception.InvalidRequestException;
import net.wojteksz128.authservice.exception.ObjectNotCorrespondingException;
import net.wojteksz128.authservice.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Component
public class ClientAppController {

    @Autowired
    private ClientAppRepository clientAppRepository;

    @Autowired
    private CreateDtoToClientAppConverter createDtoToClientAppConverter;

    @Autowired
    private DtoToClientAppConverter dtoToClientAppConverter;

    @Autowired
    private ClientAppToDtoConverter clientAppToDtoConverter;

    public List<ClientAppDto> getAllApps() {
        return clientAppRepository.findAllApps()
                .stream()
                .map(clientAppToDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public ClientAppDto createNew(CreateClientAppDto app) throws EmptyObjectException {
        if (app == null) {
            throw new EmptyObjectException("Attempt to save a null object.");
        }

        return clientAppToDtoConverter.convert(clientAppRepository.save(createDtoToClientAppConverter.convert(app)));
    }

    public ClientAppDto getAppById(Long appId) throws InvalidRequestException, ObjectNotFoundException {
        return clientAppToDtoConverter.convert(clientAppRepository.findById(appId));
    }

    public ClientAppDto getAppByGuid(String appGuid) throws InvalidRequestException, ObjectNotFoundException {
        return clientAppToDtoConverter.convert(clientAppRepository.findByGuid(appGuid));
    }

    public void updateApp(String appGuid, ClientAppDto app) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException, ObjectNotFoundException {
        checkValidity(appGuid, app);
        clientAppRepository.update(dtoToClientAppConverter.convert(app));
    }

    public void deleteApp(String appGuid, ClientAppDto app) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException, ObjectNotFoundException {
        checkValidity(appGuid, app);
        clientAppRepository.delete(dtoToClientAppConverter.convert(app));
    }

    private void checkValidity(String appGuid, ClientAppDto app) throws EmptyObjectException, InvalidRequestException, ObjectNotCorrespondingException, ObjectNotFoundException {
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
