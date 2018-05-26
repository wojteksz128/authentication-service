package net.wojteksz128.authservice.service.webapp;

import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.web.client.RestOperations;

public class RestOperationsBuilder {

    private final AuthorizationCodeResourceDetails resourceDetails;
    private final OAuth2ClientContext clientContext;


    private RestOperationsBuilder(AuthorizationCodeResourceDetails resourceDetails, OAuth2ClientContext clientContext) {
        this.resourceDetails = resourceDetails;
        this.clientContext = clientContext;
    }

    public static NeedRedirectUri create(AuthorizationCodeResourceDetails resourceDetails, OAuth2ClientContext clientContext) {
        return new NeedRedirectUri(resourceDetails, clientContext);
    }

    public RestOperations build() {
        return new OAuth2RestTemplate(resourceDetails, clientContext);
    }



    public static class NeedRedirectUri {

        private final AuthorizationCodeResourceDetails resourceDetails;
        private final OAuth2ClientContext clientContext;


        public NeedRedirectUri(AuthorizationCodeResourceDetails resourceDetails, OAuth2ClientContext clientContext) {

            this.resourceDetails = resourceDetails;
            this.clientContext = clientContext;
        }

        public RestOperationsBuilder withUri(String uri) {
            resourceDetails.setPreEstablishedRedirectUri(uri);
            return new RestOperationsBuilder(resourceDetails, clientContext);
        }
    }
}
