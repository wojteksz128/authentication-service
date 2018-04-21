package net.wojteksz128.webclient.webapp.impl;

import net.wojteksz128.authservice.service.MessageType;
import net.wojteksz128.authservice.service.clientapp.ClientAppController;
import net.wojteksz128.authservice.service.clientapp.ClientAppDto;
import net.wojteksz128.authservice.service.clientapp.CreateClientAppDto;
import net.wojteksz128.authservice.service.clientapp.OAuthClientDetailsController;
import net.wojteksz128.authservice.service.exception.EmptyObjectException;
import net.wojteksz128.authservice.service.exception.InvalidRequestException;
import net.wojteksz128.authservice.service.exception.ObjectNotCorrespondingException;
import net.wojteksz128.authservice.service.user.UserDto;
import net.wojteksz128.authservice.service.user.UserService;
import net.wojteksz128.authservice.service.webapp.WebsiteBuilder;
import net.wojteksz128.webclient.webapp.ClientAppEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

@Controller
class ClientAppEndpointImpl implements ClientAppEndpoint {

    private final ClientAppController clientAppController;
    private final UserService userService;
    private final OAuthClientDetailsController clientDetailsController;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    public ClientAppEndpointImpl(ClientAppController clientAppController, UserService userService, OAuthClientDetailsController clientDetailsController) {
        this.clientAppController = clientAppController;
        this.userService = userService;
        this.clientDetailsController = clientDetailsController;
    }

    @Override
    public String getDevApp(Authentication authentication, @RequestParam Map<String, String> params, Model model) {
        final WebsiteBuilder websiteBuilder = WebsiteBuilder.create(model).withContent("developer/devApps").withModal("modal-create").withModal("modal-info").withModal("modal-delete");
        String username = (String) authentication.getPrincipal();
        final Optional<UserDto> optionalUser = userService.findByEmail(username);

        model.addAttribute("apps", clientAppController.getAllUserApps(optionalUser.map(UserDto::getId).orElseThrow(() -> new AuthorizationServiceException("User not logged."))));

        if (params.containsKey("appAdded")) {
            websiteBuilder.withMessage(MessageType.INFO, "Sukces!", "Aplikacja \"" + params.get("clientId") + "\" została zarejestrowana.");
        }

        if (params.containsKey("error")) {
            final String ERROR_TITLE = "Błąd!";
            if (params.containsKey("add")) {
                websiteBuilder.withMessage(MessageType.ERROR, ERROR_TITLE, "Próba dodania nowej aplikacji nie powiodła się.");
            } else if (params.containsKey("info")) {
                websiteBuilder.withMessage(MessageType.ERROR, ERROR_TITLE, "Próba wyświetlenia informacji o aplikacji nie powiodła się.");
            } else if (params.containsKey("delete")) {
                websiteBuilder.withMessage(MessageType.ERROR, ERROR_TITLE, "Próba usunięcia aplikacji nie powiodła się.");
            } else {
                websiteBuilder.withMessage(MessageType.ERROR, ERROR_TITLE, "Wystąpił nieoczekiwany błąd. Skontaktuj się z administratorem w celu rozwiązania problemów.");
            }
        }
        model.addAttribute("formatter", formatter);

        return websiteBuilder.build();
    }

    @Override
    public String addDevApp(@ModelAttribute("devApp") @Valid CreateClientAppDto appDto, BindingResult result) {
        final ClientAppDto clientAppControllerNew;

        if (result.hasErrors()) {
            return "redirect:/devApp?error&add";
        }

        try {
            clientAppControllerNew = clientAppController.createNew(appDto);
            clientAppControllerNew.setClientDetailsDto(clientDetailsController.createNew(appDto.getClientDetailsDto()));
        } catch (EmptyObjectException e) {
            return "redirect:/devApp?error&add";
        }

        return "redirect:/devApp?add=" + clientAppControllerNew.getClientDetailsDto().getClientId();
    }

    @Override
    public String updateApp(@PathVariable String clientApp, @ModelAttribute("app") @Valid ClientAppDto appDto, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/devApp?error&info";
        }

        try {
            clientAppController.updateApp(clientApp, appDto);
        } catch (ObjectNotCorrespondingException | InvalidRequestException | EmptyObjectException e) {
            return "redirect:/devApp?error&info";
        }

        return "redirect:/devApp?info=" + clientApp;
    }

    @Override
    public String deleteDevApp(@PathVariable String clientApp, @ModelAttribute("devApp") @Valid ClientAppDto appDto, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/devApp?error&delete";
        }

        try {
            clientAppController.deleteApp(clientApp, appDto);
        } catch (InvalidRequestException | ObjectNotCorrespondingException | EmptyObjectException e) {
            return "redirect:/devApp?error&delete";
        }

        return "redirect:/devApp?del=&success";
    }

    //region Modal windows
    @Override
    public String showNewDevAppForm(Model model) {
        model.addAttribute("devApp", new CreateClientAppDto());

        return "developer/fragments/modalCreate";
    }

    @Override
    public String showDeleteDevAppForm(@PathVariable String clientApp, Model model) {
        model.addAttribute("devApp", clientAppController.getAppByClientId(clientApp));

        return "developer/fragments/modalDelete";
    }

    @Override
    public String getApp(@PathVariable String clientApp, Model model) {
        model.addAttribute("app", clientAppController.getAppByClientId(clientApp));
        model.addAttribute("formatter", formatter);

        return "developer/fragments/modalInfo";
    }
    //endregion
}
