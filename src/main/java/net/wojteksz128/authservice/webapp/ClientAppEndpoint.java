package net.wojteksz128.authservice.webapp;

import net.wojteksz128.authservice.clientapp.ClientAppController;
import net.wojteksz128.authservice.clientapp.ClientAppDto;
import net.wojteksz128.authservice.clientapp.CreateClientAppDto;
import net.wojteksz128.authservice.exception.EmptyObjectException;
import net.wojteksz128.authservice.exception.InvalidRequestException;
import net.wojteksz128.authservice.exception.ObjectNotCorrespondingException;
import net.wojteksz128.authservice.exception.ObjectNotFoundException;
import net.wojteksz128.authservice.user.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
class ClientAppEndpoint {

    @Autowired
    private ClientAppController clientAppController;

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping("/devApp")
    public String getDevApp(Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("apps", clientAppController.getAllUserApps(userDetails.getId()));
        model.addAttribute("devApp", new CreateClientAppDto());
        return "developer/devApps";
    }

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping(value = "/devApp/new")
    public String showNewDevAppForm(Model model) {
        model.addAttribute("devApp", new CreateClientAppDto());

        return "developer/fragments/modalCreate";
    }

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping(value = "/devApp", method = RequestMethod.POST)
    public String addDevApp(@ModelAttribute("devApp") @Valid CreateClientAppDto appDto) {
        final ClientAppDto clientAppControllerNew;

        try {
            clientAppControllerNew = clientAppController.createNew(appDto);
        } catch (EmptyObjectException e) {
            return "redirect:/devApp?error";
        }

        return "redirect:/devApp?appAdded&appkey=" + clientAppControllerNew.getGuid();
    }

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping("/devApp/{guid}")
    public String getApp(@PathVariable("guid") String guid, Model model) {
        try {
            model.addAttribute("app", clientAppController.getAppByGuid(guid));
        } catch (InvalidRequestException | ObjectNotFoundException e) {
            e.printStackTrace();
        }

        return "developer/fragments/modalInfo";
    }

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping(value = "/devApp/{guid}", method = RequestMethod.POST)
    public String updateApp(@PathVariable("guid") String guid, @ModelAttribute("app") ClientAppDto appDto) {
        try {
            clientAppController.updateApp(guid, appDto);
        } catch (ObjectNotCorrespondingException | InvalidRequestException | EmptyObjectException | ObjectNotFoundException e) {
            return "redirect:/devApp?error";
        }


        return "redirect:/devApp?success";
    }

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping(value = "/devApp/{guid}/delete")
    public String showDeleteDevAppForm(@PathVariable("guid") String guid, Model model) {
        try {
            model.addAttribute("devApp", clientAppController.getAppByGuid(guid));
        } catch (InvalidRequestException | ObjectNotFoundException e) {
            e.printStackTrace();
        }

        return "developer/fragments/modalDelete";
    }
}
