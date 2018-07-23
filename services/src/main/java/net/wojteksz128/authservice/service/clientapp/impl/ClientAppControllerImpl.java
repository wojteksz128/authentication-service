package net.wojteksz128.authservice.service.clientapp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.Collection;

@SuppressWarnings("unused")
@Component
@EnableJpaRepositories(basePackageClasses = {ClientAppRepository.class})
class ClientAppControllerImpl implements ClientAppController {

    private final ClientAppRepository clientAppRepository;

    @Autowired
    public ClientAppControllerImpl(ClientAppRepository clientAppRepository) {
        this.clientAppRepository = clientAppRepository;
    }

    @Override
    public ClientApp createNew(ClientApp app) {
        return clientAppRepository.save(app);
    }

    @Override
    public ClientApp findByClientId(String clientId) {
        return clientAppRepository.findByClientId(clientId);
    }

    @Override
    public ClientApp updateApp(ClientApp updatedApp) {
        return clientAppRepository.save(updatedApp);
    }

    @Override
    public void deleteApp(ClientApp app) {
        clientAppRepository.delete(app);
    }

    @Override
    public Collection<ClientApp> getAllUserApps(Long userId) {
        return clientAppRepository.findAllByUserId(userId);
    }
}
