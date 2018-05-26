package net.wojteksz128.authserver.endpoint;

import net.wojteksz128.authservice.service.user.UserDto;
import net.wojteksz128.authservice.service.user.UserPersonalDataDto;
import net.wojteksz128.authservice.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
class UserInfoEndpoint {

    @Autowired
    private UserService userService;


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
    @GetMapping("/{login}/personal")
    UserPersonalDataDto getPersonal(@PathVariable("login") String login) {
        return userService.findByLogin(login).map(UserDto::getPersonalData).orElse(null);
    }
}
