package net.wojteksz128.authservice.webapp;

import net.wojteksz128.authservice.clientapp.ClientAppController;
import org.springframework.beans.factory.annotation.Autowired;
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
}
