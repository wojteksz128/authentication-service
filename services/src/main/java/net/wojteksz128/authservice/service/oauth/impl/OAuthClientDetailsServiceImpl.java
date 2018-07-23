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
    private final OAuthClientDetailsDtoToEntityConverter clientDetailsDtoToEntityConverter;
    private final OAuthClientDetailsToDtoConverter clientDetailsToDtoConverter;

    @Autowired
    public OAuthClientDetailsServiceImpl(OAuthClientDetailsController clientDetailsController, OAuthClientDetailsDtoToEntityConverter clientDetailsDtoToEntityConverter, OAuthClientDetailsToDtoConverter clientDetailsToDtoConverter) {
        this.clientDetailsController = clientDetailsController;
        this.clientDetailsDtoToEntityConverter = clientDetailsDtoToEntityConverter;
        this.clientDetailsToDtoConverter = clientDetailsToDtoConverter;
    }

    @Override
    public OAuthClientDetailsDto createNew(OAuthClientDetailsDto newClientDetails) throws EmptyObjectException {
        // TODO: 23.07.2018 Check user privileges
        if (newClientDetails == null) {
            throw new EmptyObjectException("Attempt to save null OAuthClientDetails");
        }

        final OAuthClientDetails clientDetailsEntity = clientDetailsDtoToEntityConverter.convert(newClientDetails);
        final OAuthClientDetails newClientDetailsEntity = clientDetailsController.createNew(clientDetailsEntity);

        if (newClientDetailsEntity == null) {
            // TODO: 23.07.2018 Zmień wyjątek na odpowiedniejszy
            throw new EmptyObjectException("OAuthClientDetails is not created");
        }
        return clientDetailsToDtoConverter.convert(newClientDetailsEntity);
    }

    @Override
    public OAuthClientDetailsDto getByClientId(String clientId) throws EmptyObjectException {
        // TODO: 23.07.2018 Check user privileges
        if (clientId == null || clientId.isEmpty()) {
            throw new EmptyObjectException("clientId is Empty");
        }

        final OAuthClientDetails clientDetails = clientDetailsController.getByClientId(clientId);
        return clientDetailsToDtoConverter.convert(clientDetails);
    }

    @Override
    public void update(String clientId, OAuthClientDetailsDto updatedClientDetails) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException {
        // TODO: 23.07.2018 Check user privileges
        if (updatedClientDetails == null) {
            throw new EmptyObjectException("Attempt to use a null object");
        }
        if (clientId == null) {
            throw new InvalidRequestException("clientId is null");
        }
        if (!updatedClientDetails.getClientId().equals(clientId)) {
            throw new ObjectNotCorrespondingException("OAuthClientDetails is not requested object.");
        }

        final OAuthClientDetails clientDetailsEntity = clientDetailsDtoToEntityConverter.convert(updatedClientDetails);
        final OAuthClientDetails updatedClientDetailsEntity = clientDetailsController.update(clientId, clientDetailsEntity);
        if (updatedClientDetailsEntity == null) {
            // TODO: 23.07.2018 Zmień wyjątek na odpowiedniejszy
            throw new EmptyObjectException("OAuthClientDetails is not updated");
        }
    }

    @Override
    public void delete(String clientId, OAuthClientDetailsDto deletedClientDetails) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException {
        // TODO: 21.07.2018 Implement this based on OAuthClientDetailsController
        clientDetailsController.delete(clientId, deletedClientDetails);
    }
}
