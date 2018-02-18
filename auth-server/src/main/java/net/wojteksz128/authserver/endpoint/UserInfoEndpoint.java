package net.wojteksz128.authserver.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/me")
class UserInfoEndpoint {

    @GetMapping("/principal")
    Principal getPrincipal(Principal principal) {
        return principal;
    }

    @GetMapping
    String getUsername(Principal principal) {
        return principal.getName();
    }
}
