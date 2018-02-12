package net.wojteksz128.webclient.webapp;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

public interface WebAppEndpoint {
    @RequestMapping({"/", "/index"})
    String mainScreen(Principal principal, Model model);
}
