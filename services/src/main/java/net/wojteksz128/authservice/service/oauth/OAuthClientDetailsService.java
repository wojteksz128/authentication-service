package net.wojteksz128.authservice.service.oauth;

import net.wojteksz128.authservice.service.exception.EmptyObjectException;
import net.wojteksz128.authservice.service.exception.InvalidRequestException;
import net.wojteksz128.authservice.service.exception.ObjectNotCorrespondingException;

public interface OAuthClientDetailsService {

    OAuthClientDetailsDto createNew(OAuthClientDetailsDto app) throws EmptyObjectException;

    OAuthClientDetailsDto getByClientId(String clientId) throws EmptyObjectException;

    void update(String clientId, OAuthClientDetailsDto dto) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException;

    void delete(String clientId, OAuthClientDetailsDto dto) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException;
}
