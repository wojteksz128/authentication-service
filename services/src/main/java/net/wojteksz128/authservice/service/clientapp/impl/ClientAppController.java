package net.wojteksz128.authservice.service.clientapp.impl;

import java.util.Collection;

interface ClientAppController {

    ClientApp createNew(ClientApp app);

    ClientApp findByClientId(String clientId);

    ClientApp updateApp(ClientApp updatedApp);

    void deleteApp(ClientApp app);

    Collection<ClientApp> getAllUserApps(Long userId);
}
