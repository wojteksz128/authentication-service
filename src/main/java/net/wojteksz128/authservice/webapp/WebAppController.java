package net.wojteksz128.authservice.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class WebAppController {

    @RequestMapping("/signIn")
    public String greeting(Model model) {
        return "login";
    }
}
