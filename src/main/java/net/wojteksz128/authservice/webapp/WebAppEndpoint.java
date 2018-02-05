package net.wojteksz128.authservice.webapp;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public interface WebAppEndpoint {
    @RequestMapping({"/", "/index"})
    String mainScreen(Model model);
}
