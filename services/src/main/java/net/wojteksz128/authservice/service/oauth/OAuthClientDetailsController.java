package net.wojteksz128.authservice.service.oauth;

import java.util.List;

public interface OAuthClientDetailsController {

    OAuthClientDetailsDto createNew(OAuthClientDetailsDto app);

    OAuthClientDetailsDto getByClientId(String clientId);

    OAuthClientDetailsDto getByClientIds(List<String> clientIds);

    void update(String clientId, OAuthClientDetailsDto dto);

    void delete(String clientId, OAuthClientDetailsDto dto);
}
