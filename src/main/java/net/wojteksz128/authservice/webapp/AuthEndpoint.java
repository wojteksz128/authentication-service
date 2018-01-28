package net.wojteksz128.authservice.webapp;

import net.wojteksz128.authservice.user.*;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
class AuthEndpoint {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleFinder roleFinder;

    @PreAuthorize("isAnonymous()")
    @RequestMapping("/signIn")
    public String signIn(Model model) {
        return "login";
    }

    @PreAuthorize("isAuthenticated() and not hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping(value = "/user/switchToDev", method = RequestMethod.POST)
    public String switchToDev(Authentication authentication) {
        String username = ((UserDetailsImpl) authentication.getPrincipal()).getUsername();
        Optional<UserDto> userDto = userService.findByEmail(username);
        Optional<RoleDto> roleDto = roleFinder.findByCode("DEVELOPER");

        userService.addRole(userDto.orElseThrow(() -> new ObjectNotFoundException(username, "User")),
                roleDto.orElseThrow(() -> new ObjectNotFoundException("DEVELOPER", "Role")));
        return "redirect:/?switchToDev";
    }

    @RequestMapping("/403")
    public String error403(Model model) {
        return "error/403";
    }
}
