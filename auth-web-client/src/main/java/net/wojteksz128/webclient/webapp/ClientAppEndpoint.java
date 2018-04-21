package net.wojteksz128.webclient.webapp;

import net.wojteksz128.authservice.service.clientapp.ClientAppDto;
import net.wojteksz128.authservice.service.clientapp.CreateClientAppDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

public interface ClientAppEndpoint {
    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping("/devApp")
    String getDevApp(Authentication authentication, @RequestParam Map<String, String> params, Model model);

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping(value = "/devApp", method = RequestMethod.POST)
    String addDevApp(@ModelAttribute("devApp") @Valid CreateClientAppDto appDto, BindingResult result);

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping(value = "/devApp/{clientApp}", method = RequestMethod.POST)
    String updateApp(@PathVariable("clientApp") String guid, @ModelAttribute("devApp") @Valid ClientAppDto appDto, BindingResult result, Model model);

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping(value = "/devApp/{clientApp}/delete", method = RequestMethod.POST)
    String deleteDevApp(@PathVariable("clientApp") String guid, @ModelAttribute("devApp") @Valid ClientAppDto appDto, BindingResult result);

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping(value = "/devApp/new")
    String showNewDevAppForm(Model model);

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping(value = "/devApp/{clientApp}/delete")
    String showDeleteDevAppForm(@PathVariable("clientApp") String guid, Model model);

    @PreAuthorize("hasRole(\"ROLE_DEVELOPER\")")
    @RequestMapping("/devApp/{clientApp}")
    String getApp(@PathVariable("clientApp") String guid, Model model);
}
