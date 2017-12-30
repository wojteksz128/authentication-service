package net.wojteksz128.authservice.clientapp;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
class ClientAppEndpointImpl implements ClientAppEndpoint {

    @Override
    public ResponseEntity<?> getAllApps() {
        return null;
    }

    @Override
    public ResponseEntity<?> addNewClientApp(ClientApp app) {
        return null;
    }

    @Override
    public ResponseEntity<?> getApp(Long appId) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateAppInfo(Long appId, ClientApp app) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteApp(Long appId, ClientApp app) {
        return null;
    }
}
