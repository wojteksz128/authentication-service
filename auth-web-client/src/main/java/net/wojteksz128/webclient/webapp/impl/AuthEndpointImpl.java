package net.wojteksz128.webclient.webapp.impl;

import net.wojteksz128.authservice.service.user.*;
import net.wojteksz128.authservice.service.user.impl.UserDetailsImpl;
import net.wojteksz128.webclient.webapp.AuthEndpoint;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
class AuthEndpointImpl implements AuthEndpoint {

    private final UserService userService;

    private final RoleFinder roleFinder;

    @Autowired
    public AuthEndpointImpl(UserService userService, RoleFinder roleFinder) {
        this.userService = userService;
        this.roleFinder = roleFinder;
    }

//    @Override
//    public String signIn(Model model) {
//        return "login";
//    }

    @Override
    public String switchToDev(Authentication authentication) {
        String username = ((UserDetailsImpl) authentication.getPrincipal()).getUsername();
        Optional<UserDto> userDto = userService.findByEmail(username);
        Optional<RoleDto> roleDto = roleFinder.findByCode("DEVELOPER");

        userService.addRole(userDto.orElseThrow(() -> new ObjectNotFoundException(username, "User")),
                roleDto.orElseThrow(() -> new ObjectNotFoundException("DEVELOPER", "Role")));
        return "redirect:/?switchToDev";
    }

    @Override
    public String error403(Model model) {
        return "error/403";
    }
}
