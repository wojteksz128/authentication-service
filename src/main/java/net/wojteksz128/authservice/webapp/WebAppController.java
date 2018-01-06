package net.wojteksz128.authservice.webapp;

import net.wojteksz128.authservice.MessageType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class WebAppController {

    @RequestMapping({ "/", "/index" })
    public String mainScreen(Model model) {
        return "index";
    }

    @RequestMapping("/signUp")
    public String signUp(Model model) {
        return "register";
    }

    @RequestMapping("/signIn")
    public String signIn(Model model) {
        return "login";
    }

    @RequestMapping("/devApp")
    public String devapp(Model model) {
        return "developer/index";
    }
}
