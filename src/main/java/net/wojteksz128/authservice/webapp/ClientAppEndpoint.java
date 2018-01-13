package net.wojteksz128.authservice.webapp;

import net.wojteksz128.authservice.clientapp.ClientAppController;
import net.wojteksz128.authservice.user.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class ClientAppEndpoint {

    @Autowired
    private ClientAppController clientAppController;

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping("/devApp")
    public String getDevApp(Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("apps", clientAppController.getAllUserApps(userDetails.getId()));
        return "developer/devApps";
    }
}
