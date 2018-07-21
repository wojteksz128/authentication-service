package net.wojteksz128.authservice.service.oauth.impl;

import net.wojteksz128.authservice.service.exception.EmptyObjectException;
import net.wojteksz128.authservice.service.exception.InvalidRequestException;
import net.wojteksz128.authservice.service.exception.ObjectNotCorrespondingException;
import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsDto;
import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class OAuthClientDetailsServiceImpl implements OAuthClientDetailsService {

    private final OAuthClientDetailsController clientDetailsController;

    @Autowired
    public OAuthClientDetailsServiceImpl(OAuthClientDetailsController clientDetailsController) {
        this.clientDetailsController = clientDetailsController;
    }

    @Override
    public OAuthClientDetailsDto createNew(OAuthClientDetailsDto newClientDetails) throws EmptyObjectException {
        // TODO: 21.07.2018 Implement this based on OAuthClientDetailsController
        return clientDetailsController.createNew(newClientDetails);
    }

    @Override
    public OAuthClientDetailsDto getByClientId(String clientId) {
        // TODO: 21.07.2018 Implement this based on OAuthClientDetailsController
        return clientDetailsController.getByClientId(clientId);
    }

    @Override
    public void update(String clientId, OAuthClientDetailsDto updatedClientDetails) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException {
        // TODO: 21.07.2018 Implement this based on OAuthClientDetailsController
        clientDetailsController.update(clientId, updatedClientDetails);
    }

    @Override
    public void delete(String clientId, OAuthClientDetailsDto deletedClientDetails) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException {
        // TODO: 21.07.2018 Implement this based on OAuthClientDetailsController
        clientDetailsController.delete(clientId, deletedClientDetails);
    }
}
