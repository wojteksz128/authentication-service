package net.wojteksz128.webclient.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccessDeniedHandler accessDeniedHandler;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    @Value("${security.oauth2.client.access-token-uri}")
    private String accessTokenUri;

    @Value("${security.oauth2.client.user-authorization-uri}")
    private String userAuthorizationUri;

    @Value("${server.url}")
    private String serverUrl;

    @Autowired
    public WebAppSecurityConfig(AccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll()
            .and().logout().logoutSuccessUrl("/?logout")
            .and().exceptionHandling().accessDeniedPage("/403").accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/**/css/**", "/**/js/**", "/webjars/**");
    }

    @Bean
    public OAuth2ProtectedResourceDetails resource() {
        final AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();

        resource.setClientId(clientId);
        resource.setClientSecret(clientSecret);
        resource.setAccessTokenUri(accessTokenUri);
        resource.setUserAuthorizationUri(userAuthorizationUri);
//        resource.setPreEstablishedRedirectUri(serverUrl);

        return resource;
    }
}
