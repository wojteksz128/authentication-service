package net.wojteksz128.authservice.service.clientapp.impl;

import net.wojteksz128.authservice.service.clientapp.ClientAppDto;
import net.wojteksz128.authservice.service.clientapp.ClientAppService;
import net.wojteksz128.authservice.service.clientapp.CreateClientAppDto;
import net.wojteksz128.authservice.service.clientapp.UpdateClientAppDto;
import net.wojteksz128.authservice.service.exception.EmptyObjectException;
import net.wojteksz128.authservice.service.exception.InvalidRequestException;
import net.wojteksz128.authservice.service.exception.ObjectNotCorrespondingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ClientAppServiceImpl implements ClientAppService {

    private final ClientAppController clientAppController;

    @Autowired
    public ClientAppServiceImpl(ClientAppController clientAppController) {
        this.clientAppController = clientAppController;
    }

    @Override
    public ClientAppDto createNew(CreateClientAppDto newApp) throws EmptyObjectException {
        if (newApp == null) {
            throw new EmptyObjectException("Attempt to save a null ClientApp object.");
        }
        return null;
    }

    @Override
    public ClientAppDto getAppByClientId(String clientId) {
        return null;
    }

    @Override
    public void updateApp(String clientId, UpdateClientAppDto updatedApp) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException {

    }

    @Override
    public void deleteApp(String clientId, ClientAppDto deletedApp) throws ObjectNotCorrespondingException, InvalidRequestException, EmptyObjectException {

    }

    @Override
    public List<ClientAppDto> getAllUserApps(Long userId) {
        return null;
    }
}
