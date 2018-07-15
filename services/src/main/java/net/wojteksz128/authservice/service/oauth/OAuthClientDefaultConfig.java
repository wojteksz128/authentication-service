package net.wojteksz128.authservice.service.oauth;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class OAuthClientDefaultConfig {

    public List<String> getAuthorizedGrantTypes() {
        return Arrays.asList(AuthorizedGrantType.authorization_code.name(),
            AuthorizedGrantType.refresh_token.name());
    }

    public Integer getAccessTokenValidity() {
        return 24/*hours*/ * 60/*minutes*/ * 60/*seconds*/;
    }

    public Integer getRefreshTokenValidity() {
        return 3/*days*/ * 24/*hours*/ * 60/*minutes*/ * 60/*seconds*/;
    }
}
