package net.wojteksz128.authservice.service.oauth.impl;

import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsController;
import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class OAuthClientDetailsControllerImpl implements OAuthClientDetailsController {


    @Override
    public OAuthClientDetailsDto createNew(OAuthClientDetailsDto app) {
        return null;
    }

    @Override
    public OAuthClientDetailsDto getByClientId(String clientId) {
        return null;
    }

    @Override
    public OAuthClientDetailsDto getByClientIds(List<String> clientIds) {
        return null;
    }

    @Override
    public void update(String clientId, OAuthClientDetailsDto dto) {

    }

    @Override
    public void delete(String clientId, OAuthClientDetailsDto dto) {

    }
}
