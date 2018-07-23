package net.wojteksz128.authservice.service.oauth.impl;

interface OAuthClientDetailsController {

    OAuthClientDetails createNew(OAuthClientDetails app);

    OAuthClientDetails getByClientId(String clientId);

    OAuthClientDetails update(OAuthClientDetails updateClientDetails);

    void delete(OAuthClientDetails deleteClientDetails);
}
