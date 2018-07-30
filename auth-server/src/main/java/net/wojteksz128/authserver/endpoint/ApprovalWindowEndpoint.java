package net.wojteksz128.authserver.endpoint;

import net.wojteksz128.authservice.service.webapp.WebsiteBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("authorizationRequest")
class ApprovalWindowEndpoint {

    @GetMapping("/oauth/confirm_access")
    public String getAccessConfirmation(Model model) {
        return WebsiteBuilder.create(model).withContent("confirm_access").build();
    }
}
