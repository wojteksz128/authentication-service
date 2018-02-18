package net.wojteksz128.authserver.endpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomeEndpoint {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
