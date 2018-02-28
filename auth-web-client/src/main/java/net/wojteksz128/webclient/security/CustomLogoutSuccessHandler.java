package net.wojteksz128.webclient.security;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
@Primary
class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.postForEntity("http://localhost:8081/auth/oauth/revoke",
                "", HttpStatus.class,
                Collections.singletonMap("token", ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue()));

            response.sendRedirect("/?logout");
        } catch (RestClientException e) {
            response.sendRedirect("/?error");
        }

        super.onLogoutSuccess(request, response, authentication);
    }
}
