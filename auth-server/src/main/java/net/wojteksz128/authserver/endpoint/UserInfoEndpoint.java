package net.wojteksz128.authserver.endpoint;

import net.wojteksz128.authservice.service.user.UserPersonalDataDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

public interface UserInfoEndpoint {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me/principal")
    Principal getPrincipal(Principal principal);

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    String getUsername(Principal principal);

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{login}/personal")
    UserPersonalDataDto getPersonal(@PathVariable("login") String login);

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{login}/personal")
    UserPersonalDataDto updatePersonalData(@PathVariable("login") String login, @RequestBody UserPersonalDataDto personalData);
}
