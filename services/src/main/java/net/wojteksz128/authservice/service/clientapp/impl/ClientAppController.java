package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.clientapp.ClientAppDto;
import net.wojteksz128.authservice.service.exception.EmptyObjectException;
import net.wojteksz128.authservice.service.exception.InvalidRequestException;
import net.wojteksz128.authservice.service.exception.ObjectNotCorrespondingException;

import java.util.List;

interface ClientAppController {

    ClientApp createNew(ClientApp app);

    ClientApp findByClientId(String clientId);

    ClientApp updateApp(ClientApp updatedApp);

    void deleteApp(String appGuid, ClientAppDto app) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException;

    List<ClientAppDto> getAllUserApps(Long userId);
}
