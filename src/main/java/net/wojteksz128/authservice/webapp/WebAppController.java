package net.wojteksz128.authservice.webapp;

import net.wojteksz128.authservice.MessageType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class WebAppController {

    @RequestMapping("/signIn")
    public String greeting(Model model) {
        model.addAttribute("message_type", MessageType.ERROR);
        model.addAttribute("message_title", "Błąd!");
        model.addAttribute("message_content", "Podano nieprawidłowy e-mail lub hasło.");
        model.addAttribute("message_show", true);
        return "login";
    }
}
