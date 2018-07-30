package net.wojteksz128.authserver.endpoint;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("authorizationRequest")
class ApprovalWindowEndpoint {

    @GetMapping("/oauth/confirm_access")
    public String getAccessConfirmation(Model model, HttpServletRequest request) {
        model.addAttribute("defaultScopes", request.getAttribute("scopes"));
        return "confirm_access";
    }
}
