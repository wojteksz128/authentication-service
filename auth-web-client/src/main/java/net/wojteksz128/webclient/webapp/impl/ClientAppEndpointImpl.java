package net.wojteksz128.webclient.webapp.impl;

import net.wojteksz128.authservice.service.MessageType;
import net.wojteksz128.authservice.service.clientapp.*;
import net.wojteksz128.authservice.service.exception.EmptyObjectException;
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

    private final ClientAppService clientAppService;
    private final UserService userService;
    private final DateTimeFormatter formatter;
    private final ClientAppDtoToUpdateClientAppDtoConverter clientAppDtoToUpdateClientAppDtoConverter;

    @Autowired
    public ClientAppEndpointImpl(ClientAppService clientAppService, UserService userService, ClientAppDtoToUpdateClientAppDtoConverter clientAppDtoToUpdateClientAppDtoConverter) {
        this.clientAppService = clientAppService;
        this.userService = userService;
        this.clientAppDtoToUpdateClientAppDtoConverter = clientAppDtoToUpdateClientAppDtoConverter;
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String getDevApp(Authentication authentication, @RequestParam Map<String, String> params, Model model) {
        final WebsiteBuilder websiteBuilder = WebsiteBuilder.create(model).withContent("developer/devApps").withModal("modal-create").withModal("modal-info").withModal("modal-delete");
        String username = (String) authentication.getPrincipal();
        final Optional<UserDto> optionalUser = userService.findByLogin(username);

        model.addAttribute("apps", clientAppService.getAllUserApps(optionalUser.map(UserDto::getId).orElseThrow(() -> new AuthorizationServiceException("User not logged."))));

        if (params.containsKey("add")) {
            websiteBuilder.withMessage(MessageType.INFO, "Sukces!", "Aplikacja \"" + params.get("add") + "\" została zarejestrowana.");
        }

        if (params.containsKey("info")) {
            websiteBuilder.withMessage(MessageType.INFO, "Sukces!", "Informacje o aplikacji \"" + params.get("info") + "\" zostały zaktualizowane.");
        }

        if (params.containsKey("del")) {
            websiteBuilder.withMessage(MessageType.INFO, "Sukces!", "Aplikacja \"" + params.get("del") + "\" została usunięta.");
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
            return "developer/fragments/modalCreate";
        }

        try {
            clientAppControllerNew = clientAppService.createNew(appDto);
        } catch (Exception e) {
            result.reject("global", null, e.getLocalizedMessage());
            return "developer/fragments/modalCreate";
        }

        return "redirect:/devApp?add=" + clientAppControllerNew.getClientDetailsDto().getClientId();
    }

    @Override
    public String updateApp(@PathVariable String clientApp, @ModelAttribute("devApp") @Valid UpdateClientAppDto appDto, BindingResult result, Model model) {
        model.addAttribute("formatter", formatter);

        if (result.hasErrors()) {
            return "developer/fragments/modalInfo";
        }

        try {
            clientAppService.updateApp(clientApp, appDto);
        } catch (Exception e) {
            result.reject("global", null, e.getLocalizedMessage());
            return "developer/fragments/modalInfo";
        }

        return "redirect:/devApp?info=" + clientApp;
    }

    @Override
    public String deleteDevApp(@PathVariable String clientApp, @ModelAttribute("devApp") @Valid ClientAppDto appDto, BindingResult result) {
        if (result.hasErrors()) {
            return "developer/fragments/modalDelete";
        }

        try {
            clientAppService.deleteApp(clientApp, appDto);
        } catch (Exception e) {
            result.reject("global", null, e.getLocalizedMessage());
            return "developer/fragments/modalDelete";
        }

        return "redirect:/devApp?del=" + clientApp;
    }

    //region Modal windows
    @Override
    public String showNewDevAppForm(Model model) {
        model.addAttribute("devApp", new CreateClientAppDto());

        return "developer/fragments/modalCreate";
    }

    @Override
    public String showDeleteDevAppForm(@PathVariable String clientApp, Model model) {
        try {
            model.addAttribute("devApp", clientAppService.getAppByClientId(clientApp));
        } catch (EmptyObjectException e) {
            e.printStackTrace();    // TODO: 21.07.2018 Do it better
        }

        return "developer/fragments/modalDelete";
    }

    @Override
    public String getApp(@PathVariable String clientApp, Model model) {
        try {
            model.addAttribute("devApp", clientAppDtoToUpdateClientAppDtoConverter.convert(clientAppService.getAppByClientId(clientApp)));
        } catch (EmptyObjectException e) {
            e.printStackTrace();    // TODO: 21.07.2018 Do it better
        }
        model.addAttribute("formatter", formatter);

        return "developer/fragments/modalInfo";
    }
    //endregion
}
