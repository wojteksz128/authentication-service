package net.wojteksz128.authserver.endpoint;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public interface LoginEndpoint {

    @GetMapping("/login")
    String login(Model model);
}
