package net.wojteksz128.authservice.webapp;

import net.wojteksz128.authservice.clientapp.ClientAppController;
import net.wojteksz128.authservice.clientapp.ClientAppDto;
import net.wojteksz128.authservice.clientapp.CreateClientAppDto;
import net.wojteksz128.authservice.exception.EmptyObjectException;
import net.wojteksz128.authservice.exception.InvalidRequestException;
import net.wojteksz128.authservice.exception.ObjectNotCorrespondingException;
import net.wojteksz128.authservice.user.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
class ClientAppEndpoint {

    @Autowired
    private ClientAppController clientAppController;

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping("/devApp")
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

        return "developer/devApps";
    }

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping(value = "/devApp", method = RequestMethod.POST)
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

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping(value = "/devApp/{guid}", method = RequestMethod.POST)
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

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping(value = "/devApp/{guid}/delete", method = RequestMethod.POST)
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

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping(value = "/devApp/new")
    public String showNewDevAppForm(Model model) {
        model.addAttribute("devApp", new CreateClientAppDto());

        return "developer/fragments/modalCreate";
    }

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping(value = "/devApp/{guid}/delete")
    public String showDeleteDevAppForm(@PathVariable("guid") String guid, Model model) {
        model.addAttribute("devApp", clientAppController.getAppByGuid(guid));

        return "developer/fragments/modalDelete";
    }

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping("/devApp/{guid}")
    public String getApp(@PathVariable("guid") String guid, Model model) {
        model.addAttribute("app", clientAppController.getAppByGuid(guid));

        return "developer/fragments/modalInfo";
    }
}
