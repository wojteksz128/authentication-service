package net.wojteksz128.authservice.webapp;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class WebAppController {

    @RequestMapping({"/", "/index"})
    public String mainScreen(Model model) {
        return "index";
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping("/signUp")
    public String signUp(Model model) {
        return "register";
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping("/signIn")
    public String signIn(Model model) {
        return "login";
    }

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping("/devApp")
    public String devApp(Model model) {
        return "developer/devApps";
    }

    @RequestMapping("/403")
    public String error403(Model model) {
        return "error/403";
    }
}
