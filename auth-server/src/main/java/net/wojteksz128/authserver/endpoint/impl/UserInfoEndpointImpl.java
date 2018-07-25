package net.wojteksz128.authserver.endpoint.impl;

import net.wojteksz128.authserver.endpoint.UserInfoEndpoint;
import net.wojteksz128.authservice.service.user.UserDto;
import net.wojteksz128.authservice.service.user.UserPersonalDataDto;
import net.wojteksz128.authservice.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
class UserInfoEndpointImpl implements UserInfoEndpoint {

    private final UserService userService;

    @Autowired
    public UserInfoEndpointImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me/principal")
    public Principal getPrincipal(Principal principal) {
        return principal;
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public String getUsername(Principal principal) {
        return principal.getName();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me/personal")
    public UserPersonalDataDto getPersonal() {
        return userService.getCurrentLoggedUser().map(UserDto::getPersonalData).orElse(null);
    }
}
