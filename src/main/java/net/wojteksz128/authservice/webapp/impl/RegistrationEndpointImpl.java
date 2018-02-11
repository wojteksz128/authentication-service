package net.wojteksz128.authservice.webapp.impl;

import net.wojteksz128.authservice.service.user.UserRegistrationDto;
import net.wojteksz128.authservice.service.user.UserService;
import net.wojteksz128.authservice.webapp.RegistrationEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/signUp")
@SuppressWarnings("unused")
class RegistrationEndpointImpl implements RegistrationEndpoint {

    private final UserService userService;

    @Autowired
    public RegistrationEndpointImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @Override
    @PreAuthorize("isAnonymous()")
    @GetMapping
    public String signUp(Model model) {
        return "register";
    }

    @Override
    @PreAuthorize("isAnonymous()")
    @PostMapping
    public String register(@ModelAttribute("user") @Valid UserRegistrationDto user, BindingResult result) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            result.rejectValue("confirmPassword", null, "The password fields must match");
        }

        if (result.hasErrors()) {
            return "register";
        }

        userService.save(user);
        return "redirect:/signIn?registered";
    }
}
