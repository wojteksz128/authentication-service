package net.wojteksz128.webclient.webapp.impl;

import net.wojteksz128.authservice.service.user.RoleDto;
import net.wojteksz128.authservice.service.user.RoleFinder;
import net.wojteksz128.authservice.service.user.UserDto;
import net.wojteksz128.authservice.service.user.UserService;
import net.wojteksz128.webclient.webapp.AuthEndpoint;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Override
    public String switchToDev(Authentication authentication) {
        Optional<UserDto> userDto = userService.getCurrentLoggedUser();
        Optional<RoleDto> roleDto = roleFinder.findByCode("DEVELOPER");

        userService.addRole(userDto.orElseThrow(() -> new ObjectNotFoundException("user", "User")),
            roleDto.orElseThrow(() -> new ObjectNotFoundException("DEVELOPER", "Role")));
        return "redirect:/?switchToDev";
    }

//    @Override
//    public String logout() {
//        SecurityContextHolder.clearContext();
//
//        return "redirect:/?logout";
//    }

    @Override
    public String error403(Model model) {
        return "error/403";
    }
}
