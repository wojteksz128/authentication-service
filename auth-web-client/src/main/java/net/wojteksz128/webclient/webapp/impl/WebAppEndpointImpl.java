package net.wojteksz128.webclient.webapp.impl;

import net.wojteksz128.authservice.service.webapp.WebsiteBuilder;
import net.wojteksz128.webclient.webapp.WebAppEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class WebAppEndpointImpl implements WebAppEndpoint {

    @Override
    @RequestMapping({"/", "/index"})
    public String mainScreen(Model model) {
        return WebsiteBuilder.create(model).withContent("index").build();
    }
}
