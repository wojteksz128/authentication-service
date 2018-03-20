package net.wojteksz128.authserver.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
class UserInfoEndpoint {

    @GetMapping("/me/principal")
    Principal getPrincipal(Principal principal) {
        return principal;
    }

    @GetMapping("/me")
    String getUsername(Principal principal) {
        return principal.getName();
    }
}
