package net.wojteksz128.authservice.clientapp;

import net.wojteksz128.authservice.exception.EmptyObjectException;
import net.wojteksz128.authservice.exception.InvalidRequestException;
import net.wojteksz128.authservice.exception.ObjectNotCorrespondingException;
import net.wojteksz128.authservice.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClientAppController {

    @Autowired
    private ClientAppRepository clientAppRepository;

    public List<ClientApp> getAllApps() {
        return clientAppRepository.findAllApps();
    }

    public ClientApp createNew(ClientApp app) throws EmptyObjectException {
        if (app == null) {
            throw new EmptyObjectException("Attempt to save a null object.");
        }

        return clientAppRepository.save(app);
    }

    public Optional<ClientApp> getAppById(Long appId) {
        return clientAppRepository.findById(appId);
    }

    public Optional<ClientApp> getAppByGuid(String appGuid) {
        return clientAppRepository.findByGuid(appGuid);
    }

    public void updateApp(String appGuid, ClientApp app) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException, ObjectNotFoundException {
        checkValidity(appGuid, app);
        clientAppRepository.update(app);
    }

    public void deleteApp(String appGuid, ClientApp app) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException, ObjectNotFoundException {
        checkValidity(appGuid, app);
        clientAppRepository.delete(app);
    }

    private void checkValidity(String appGuid, ClientApp app) throws EmptyObjectException, InvalidRequestException, ObjectNotCorrespondingException, ObjectNotFoundException {
        if (app == null) {
            throw new EmptyObjectException("Attempt to save a null object.");
        }

        if (appGuid == null) {
            throw new InvalidRequestException("App guid is null.");
        }

        if (! appGuid.equals(app.getGuid())) {
            throw new ObjectNotCorrespondingException("Object is not requested object.");
        }

        if (! clientAppRepository.findByGuid(appGuid).isPresent()) {
            throw new ObjectNotFoundException("App is not finded.");
        }
    }
}
