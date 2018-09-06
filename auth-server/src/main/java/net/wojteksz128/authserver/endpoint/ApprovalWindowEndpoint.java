package net.wojteksz128.authserver.endpoint;

import net.wojteksz128.authservice.service.UserDetailsType;
import net.wojteksz128.authservice.service.clientapp.ClientAppService;
import net.wojteksz128.authservice.service.exception.EmptyObjectException;
import net.wojteksz128.authservice.service.webapp.WebsiteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("authorizationRequest")
class ApprovalWindowEndpoint {

    private final ClientAppService clientAppService;

    @Autowired
    public ApprovalWindowEndpoint(ClientAppService clientAppService) {
        this.clientAppService = clientAppService;
    }

    @GetMapping("/oauth/confirm_access")
    public String getAccessConfirmation(Model model) throws EmptyObjectException {
        final AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.asMap().get("authorizationRequest");

        model.addAttribute("appName", clientAppService.getAppByClientId(authorizationRequest.getClientId()).getName());

        final List<UserDetailsType> scopes = authorizationRequest.getScope().stream().map(UserDetailsType::valueOf).collect(Collectors.toList());
        model.addAttribute("scopes", scopes);

        return WebsiteBuilder.create(model).withContent("confirm_access").build();
    }
}
