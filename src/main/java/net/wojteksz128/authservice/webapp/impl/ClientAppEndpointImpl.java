package net.wojteksz128.authservice.webapp.impl;

import net.wojteksz128.authservice.clientapp.ClientAppController;
import net.wojteksz128.authservice.clientapp.ClientAppDto;
import net.wojteksz128.authservice.clientapp.CreateClientAppDto;
import net.wojteksz128.authservice.exception.EmptyObjectException;
import net.wojteksz128.authservice.exception.InvalidRequestException;
import net.wojteksz128.authservice.exception.ObjectNotCorrespondingException;
import net.wojteksz128.authservice.user.impl.UserDetailsImpl;
import net.wojteksz128.authservice.webapp.ClientAppEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
class ClientAppEndpointImpl implements ClientAppEndpoint {

    private final ClientAppController clientAppController;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    public ClientAppEndpointImpl(ClientAppController clientAppController) {
        this.clientAppController = clientAppController;
    }

    @Override
    public String getDevApp(Authentication authentication, @RequestParam Map<String, String> params, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        model.addAttribute("apps", clientAppController.getAllUserApps(userDetails.getId()));
        if (params.containsKey("error")) {
            if (params.containsKey("add")) {
                model.addAttribute("add", "");
            } else if (params.containsKey("info")) {
                model.addAttribute("info", "");
            } else if (params.containsKey("delete")) {
                model.addAttribute("delete", "");
            }
        }
        model.addAttribute("formatter", formatter);

        return "developer/devApps";
    }

    @Override
    public String addDevApp(@ModelAttribute("devApp") @Valid CreateClientAppDto appDto, BindingResult result) {
        final ClientAppDto clientAppControllerNew;

        if (result.hasErrors()) {
            return "redirect:/devApp?error&add";
        }

        try {
            clientAppControllerNew = clientAppController.createNew(appDto);
        } catch (EmptyObjectException e) {
            return "redirect:/devApp?error&add";
        }

        return "redirect:/devApp?appAdded&appkey=" + clientAppControllerNew.getGuid();
    }

    @Override
    public String updateApp(@PathVariable("guid") String guid, @ModelAttribute("app") @Valid ClientAppDto appDto, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/devApp?error&info";
        }

        try {
            clientAppController.updateApp(guid, appDto);
        } catch (ObjectNotCorrespondingException | InvalidRequestException | EmptyObjectException e) {
            return "redirect:/devApp?error&info";
        }

        return "redirect:/devApp?success";
    }

    @Override
    public String deleteDevApp(@PathVariable("guid") String guid, @ModelAttribute("app") @Valid ClientAppDto appDto, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/devApp?error&delete";
        }

        try {
            clientAppController.deleteApp(guid, appDto);
        } catch (InvalidRequestException | ObjectNotCorrespondingException | EmptyObjectException e) {
            return "redirect:/devApp?error&delete";
        }

        return "redirect:/devApp?success";
    }

    //----- Modal windows ----------------------------------------------------------------------------------------------

    @Override
    public String showNewDevAppForm(Model model) {
        model.addAttribute("devApp", new CreateClientAppDto());

        return "developer/fragments/modalCreate";
    }

    @Override
    public String showDeleteDevAppForm(@PathVariable("guid") String guid, Model model) {
        model.addAttribute("devApp", clientAppController.getAppByGuid(guid));

        return "developer/fragments/modalDelete";
    }

    @Override
    public String getApp(@PathVariable("guid") String guid, Model model) {
        model.addAttribute("app", clientAppController.getAppByGuid(guid));
        model.addAttribute("formatter", formatter);

        return "developer/fragments/modalInfo";
    }
}
