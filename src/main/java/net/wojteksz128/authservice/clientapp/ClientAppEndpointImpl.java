package net.wojteksz128.authservice.clientapp;

import net.wojteksz128.authservice.exception.EmptyObjectException;
import net.wojteksz128.authservice.exception.InvalidRequestException;
import net.wojteksz128.authservice.exception.ObjectNotCorrespondingException;
import net.wojteksz128.authservice.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Component
class ClientAppEndpointImpl implements ClientAppEndpoint {

    @Autowired
    private ClientAppController clientAppController;

    @Override
    public ResponseEntity<?> getAllApps() {
        return new ResponseEntity<>(clientAppController.getAllApps(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addNewClientApp(@RequestBody ClientApp app) {
        ResponseEntity<?> response;

        try {
            response = new ResponseEntity<>(clientAppController.createNew(app), HttpStatus.CREATED);
        } catch (EmptyObjectException | Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

        return response;
    }

    @Override
    public ResponseEntity<?> getApp(@PathVariable("guid") String appGuid) {
        Optional<ClientApp> findApp = clientAppController.getAppByGuid(appGuid);

        return findApp.<ResponseEntity<?>>map(app -> new ResponseEntity<>(app, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("App not found.", HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<?> updateApp(@PathVariable("guid") String appGuid, @RequestBody ClientApp app) {
        ResponseEntity<?> response;

        try {
            clientAppController.updateApp(appGuid, app);
            response = new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (ObjectNotCorrespondingException | EmptyObjectException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (InvalidRequestException | ObjectNotFoundException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @Override
    public ResponseEntity<?> deleteApp(@PathVariable("guid") String appGuid, @RequestBody ClientApp app) {
        ResponseEntity<?> response;

        try {
            clientAppController.deleteApp(appGuid, app);
            response = new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (ObjectNotCorrespondingException | EmptyObjectException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (InvalidRequestException | ObjectNotFoundException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return response;
    }
}
