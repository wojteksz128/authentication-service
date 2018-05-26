package net.wojteksz128.webclient.webapp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import java.security.Principal;

@RestController
class UserInfoEndpoint {

    private final AuthorizationCodeResourceDetails resourceDetails;
    private final OAuth2ClientContext clientContext;


    @Autowired
    public UserInfoEndpoint(AuthorizationCodeResourceDetails resourceDetails, OAuth2ClientContext clientContext) {
        this.resourceDetails = resourceDetails;
        this.clientContext = clientContext;
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me/principal")
    Principal getPrincipal(Principal principal) {
        return principal;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    String getUsername(Principal principal) {
        return principal.getName();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me/personal")
    String getUserPersonalData(Principal principal) {
        final String url = "http://localhost:8080/auth/me/personal";
        final RestOperations restOperations = new OAuth2RestTemplate(resourceDetails, clientContext);

        resourceDetails.setPreEstablishedRedirectUri(url);
        ResponseEntity<String> responseEntity = restOperations.getForEntity(url, String.class);
        return responseEntity.getBody();
    }
}
