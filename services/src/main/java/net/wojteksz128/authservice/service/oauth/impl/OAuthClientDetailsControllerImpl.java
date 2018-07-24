package net.wojteksz128.authservice.service.oauth.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@Component
@EnableJpaRepositories(basePackageClasses = {OAuthClientDetailsRepository.class})
class OAuthClientDetailsControllerImpl implements OAuthClientDetailsController {

    private final OAuthClientDetailsRepository clientDetailsRepository;

    @Autowired
    public OAuthClientDetailsControllerImpl(OAuthClientDetailsRepository clientDetailsRepository) {

        this.clientDetailsRepository = clientDetailsRepository;
    }

    @Override
    public OAuthClientDetails createNew(OAuthClientDetails clientDetailsDto) {
        return clientDetailsRepository.save(clientDetailsDto);
    }

    @Override
    public OAuthClientDetails getByClientId(String clientId) {
        return clientDetailsRepository.findByClientId(clientId);
    }

    @Override
    public OAuthClientDetails update(OAuthClientDetails updateClientDetails) {
        return clientDetailsRepository.save(updateClientDetails);
    }

    @Override
    public void delete(OAuthClientDetails deleteClientDetails) {
        clientDetailsRepository.delete(deleteClientDetails);
    }
}
