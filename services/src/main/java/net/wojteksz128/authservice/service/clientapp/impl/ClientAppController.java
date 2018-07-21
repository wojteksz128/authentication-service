package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.clientapp.ClientAppDto;

import java.util.List;

interface ClientAppController {

    ClientApp createNew(ClientApp app);

    ClientApp findByClientId(String clientId);

    ClientApp updateApp(ClientApp updatedApp);

    void deleteApp(ClientApp app);

    List<ClientAppDto> getAllUserApps(Long userId);
}
