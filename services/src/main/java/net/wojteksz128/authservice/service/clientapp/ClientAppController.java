package net.wojteksz128.authservice.service.clientapp;

import net.wojteksz128.authservice.service.exception.EmptyObjectException;
import net.wojteksz128.authservice.service.exception.InvalidRequestException;
import net.wojteksz128.authservice.service.exception.ObjectNotCorrespondingException;

import java.util.List;

public interface ClientAppController {

    ClientAppDto createNew(CreateClientAppDto app) throws EmptyObjectException;

    ClientAppDto getAppByClientId(String clientId);

    void updateApp(String appGuid, UpdateClientAppDto app) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException;

    void deleteApp(String appGuid, ClientAppDto app) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException;

    List<ClientAppDto> getAllUserApps(Long userId);
}
