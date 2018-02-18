package net.wojteksz128.webclient.webapp;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface AuthEndpoint {

    @PreAuthorize("isAuthenticated() and not hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping(value = "/user/switchToDev", method = RequestMethod.POST)
    String switchToDev(Authentication authentication);

    @RequestMapping("/403")
    String error403(Model model);
}
