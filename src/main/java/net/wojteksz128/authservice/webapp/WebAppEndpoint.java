package net.wojteksz128.authservice.webapp;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class WebAppEndpoint {

    @RequestMapping({"/", "/index"})
    public String mainScreen(Model model) {
        return "index";
    }

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping("/devApp")
    public String devApp(Model model) {
        return "developer/devApps";
    }
}
