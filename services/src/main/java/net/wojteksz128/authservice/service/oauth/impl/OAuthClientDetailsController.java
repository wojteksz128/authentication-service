package net.wojteksz128.authservice.service.oauth.impl;

import net.wojteksz128.authservice.service.exception.EmptyObjectException;
import net.wojteksz128.authservice.service.exception.InvalidRequestException;
import net.wojteksz128.authservice.service.exception.ObjectNotCorrespondingException;
import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsDto;

interface OAuthClientDetailsController {

    OAuthClientDetails createNew(OAuthClientDetails app);

    OAuthClientDetails getByClientId(String clientId);

    OAuthClientDetails update(String clientId, OAuthClientDetails updateClientDetails) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException;

    void delete(String clientId, OAuthClientDetailsDto dto) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException;
}
