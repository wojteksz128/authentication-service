package net.wojteksz128.authserver.endpoint;

import net.wojteksz128.authservice.service.webapp.WebsiteBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class LoginEndpoint {

    @GetMapping("/login")
    public String login(Model model) {
        return WebsiteBuilder.create(model).withContent("login").build();
    }
}
