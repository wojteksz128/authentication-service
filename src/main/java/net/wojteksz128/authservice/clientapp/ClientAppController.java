package net.wojteksz128.authservice.clientapp;

import net.wojteksz128.authservice.exception.EmptyObjectException;
import net.wojteksz128.authservice.exception.InvalidRequestException;
import net.wojteksz128.authservice.exception.ObjectNotCorrespondingException;

import java.util.List;

public interface ClientAppController {

    ClientAppDto createNew(CreateClientAppDto app) throws EmptyObjectException;

    ClientAppDto getAppByGuid(String appGuid);

    void updateApp(String appGuid, ClientAppDto app) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException;

    void deleteApp(String appGuid, ClientAppDto app) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException;

    List<ClientAppDto> getAllUserApps(Long userId);
}
