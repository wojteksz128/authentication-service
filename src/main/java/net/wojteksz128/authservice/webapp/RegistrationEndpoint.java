package net.wojteksz128.authservice.webapp;

import net.wojteksz128.authservice.service.user.UserRegistrationDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

public interface RegistrationEndpoint {
    @ModelAttribute("user")
    UserRegistrationDto userRegistrationDto();

    @PreAuthorize("isAnonymous()")
    @GetMapping
    String signUp(Model model);

    @PreAuthorize("isAnonymous()")
    @PostMapping
    String register(@ModelAttribute("user") @Valid UserRegistrationDto user, BindingResult result);
}
