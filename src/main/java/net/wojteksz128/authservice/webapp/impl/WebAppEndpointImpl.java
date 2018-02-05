package net.wojteksz128.authservice.webapp.impl;

import net.wojteksz128.authservice.webapp.WebAppEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class WebAppEndpointImpl implements WebAppEndpoint {

    @Override
    @RequestMapping({"/", "/index"})
    public String mainScreen(Model model) {
        return "index";
    }
}
