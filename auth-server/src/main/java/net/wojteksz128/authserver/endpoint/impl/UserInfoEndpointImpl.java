package net.wojteksz128.authserver.endpoint.impl;

import net.wojteksz128.authserver.endpoint.UserInfoEndpoint;
import net.wojteksz128.authservice.service.user.UserDto;
import net.wojteksz128.authservice.service.user.UserPersonalDataDto;
import net.wojteksz128.authservice.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
class UserInfoEndpointImpl implements UserInfoEndpoint {

    @SuppressWarnings("FieldCanBeLocal")
    private final String CURRENT_LOGGED_KEY = "me";
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
    @GetMapping("/{login}/personal")
    public UserPersonalDataDto getPersonal(@PathVariable("login") String login) {
        final Optional<UserDto> userDto;
        if (login.equals(CURRENT_LOGGED_KEY)) {
            userDto = userService.getCurrentLoggedUser();
        } else {
            userDto = userService.findByLogin(login);
        }
        return userDto.map(UserDto::getPersonalData).orElse(null);
    }

    @Override
    public UserPersonalDataDto updatePersonalData(String login, UserPersonalDataDto personalData) {
        // TODO: 26.07.2018 Implement updating personal data
        return null;
    }
}
