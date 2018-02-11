package net.wojteksz128.authserver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomeEndpoint {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
