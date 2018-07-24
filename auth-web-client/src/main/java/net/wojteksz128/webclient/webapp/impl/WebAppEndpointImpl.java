package net.wojteksz128.webclient.webapp.impl;

import net.wojteksz128.authservice.service.MessageType;
import net.wojteksz128.authservice.service.webapp.WebsiteBuilder;
import net.wojteksz128.webclient.webapp.WebAppEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
class WebAppEndpointImpl implements WebAppEndpoint {

    @Override
    public String mainScreen(Model model, @RequestParam Map<String, String> params) {
        final WebsiteBuilder websiteBuilder = WebsiteBuilder.create(model).withContent("index");

        if (params.containsKey("logout")) {
            websiteBuilder.withMessage(MessageType.INFO, "Wylogowano.", "Użytkownik został wylogowany.");
        }

        if (params.containsKey("switchToDev")) {
            websiteBuilder.withMessage(MessageType.INFO, "Zarejestrowano jako developer.", "Zostałeś zarejestrowany jako developer. Po ponownym zalogowaniu będzie możliwe dodawanie własnych aplikacji do systemu.");
        }

        return websiteBuilder.build();
    }
}
