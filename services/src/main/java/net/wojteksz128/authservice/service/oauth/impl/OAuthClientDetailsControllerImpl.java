package net.wojteksz128.authservice.service.oauth.impl;

import net.wojteksz128.authservice.service.exception.EmptyObjectException;
import net.wojteksz128.authservice.service.exception.InvalidRequestException;
import net.wojteksz128.authservice.service.exception.ObjectNotCorrespondingException;
import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsController;
import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@Component
@EnableJpaRepositories(basePackageClasses = {OAuthClientDetailsRepository.class})
class OAuthClientDetailsControllerImpl implements OAuthClientDetailsController {

    private final OAuthClientDetailsDtoToEntityConverter clientDetailsDtoToEntityConverter;
    private final OAuthClientDetailsToDtoConverter clientDetailsEntityToDtoConverter;
    private final OAuthClientDetailsRepository clientDetailsRepository;

    @Autowired
    public OAuthClientDetailsControllerImpl(OAuthClientDetailsDtoToEntityConverter clientDetailsDtoToEntityConverter, OAuthClientDetailsToDtoConverter clientDetailsEntityToDtoConverter, OAuthClientDetailsRepository clientDetailsRepository) {

        this.clientDetailsDtoToEntityConverter = clientDetailsDtoToEntityConverter;
        this.clientDetailsEntityToDtoConverter = clientDetailsEntityToDtoConverter;
        this.clientDetailsRepository = clientDetailsRepository;
    }

    @Override
    public OAuthClientDetailsDto createNew(OAuthClientDetailsDto clientDetailsDto) throws EmptyObjectException {
        if (clientDetailsDto == null) {
            throw new EmptyObjectException("Attempt to save null object.");
        }

        return clientDetailsEntityToDtoConverter.convert(clientDetailsRepository.save(clientDetailsDtoToEntityConverter.convert(clientDetailsDto)));
    }

    @Override
    public OAuthClientDetailsDto getByClientId(String clientId) {
        return clientDetailsEntityToDtoConverter.convert(clientDetailsRepository.findByClientId(clientId));
    }

    @Override
    public void update(String clientId, OAuthClientDetailsDto dto) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException {
        checkValidity(clientId, dto);
        clientDetailsRepository.save(clientDetailsDtoToEntityConverter.convert(dto));
    }

    @Override
    public void delete(String clientId, OAuthClientDetailsDto dto) {

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
    private void checkValidity(String clientId, OAuthClientDetailsDto dto) throws EmptyObjectException, InvalidRequestException, ObjectNotCorrespondingException {
        if (dto == null) {
            throw new EmptyObjectException("Attempt to use a null object.");
        }

        if (clientId == null) {
            throw new InvalidRequestException("Client Id is null.");
        }

        if (!clientId.equals(dto.getClientId())) {
            throw new ObjectNotCorrespondingException("Object is not requested object.");
        }
    }
}
