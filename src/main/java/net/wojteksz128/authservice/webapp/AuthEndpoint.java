package net.wojteksz128.authservice.webapp;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class AuthEndpoint {

    @PreAuthorize("isAnonymous()")
    @RequestMapping("/signIn")
    public String signIn(Model model) {
        return "login";
    }

    @RequestMapping("/403")
    public String error403(Model model) {
        return "error/403";
    }
}
