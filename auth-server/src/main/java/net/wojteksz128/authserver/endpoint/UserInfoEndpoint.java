package net.wojteksz128.authserver.endpoint;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
class UserInfoEndpoint {

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
    String getPersonal() {
        return "testowe dane z UserInfoEndpoint";
    }
}
