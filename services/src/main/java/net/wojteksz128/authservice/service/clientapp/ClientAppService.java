package net.wojteksz128.authservice.service.clientapp;

import net.wojteksz128.authservice.service.exception.EmptyObjectException;
import net.wojteksz128.authservice.service.exception.InvalidRequestException;
import net.wojteksz128.authservice.service.exception.ObjectNotCorrespondingException;

import java.util.List;

public interface ClientAppService {

    ClientAppDto createNew(CreateClientAppDto newApp) throws EmptyObjectException;

    ClientAppDto getAppByClientId(String clientId);

    void updateApp(String clientId, UpdateClientAppDto updatedApp) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException;

    void deleteApp(String clientId, ClientAppDto deletedApp) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException;

    List<ClientAppDto> getAllUserApps(Long userId);
}
