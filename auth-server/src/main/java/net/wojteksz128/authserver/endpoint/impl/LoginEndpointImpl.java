package net.wojteksz128.authserver.endpoint.impl;

import net.wojteksz128.authserver.endpoint.LoginEndpoint;
import net.wojteksz128.authservice.service.webapp.WebsiteBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class LoginEndpointImpl implements LoginEndpoint {

    @Override
    @GetMapping("/login")
    public String login(Model model) {
        return WebsiteBuilder.create(model).withContent("login").build();
    }
}
