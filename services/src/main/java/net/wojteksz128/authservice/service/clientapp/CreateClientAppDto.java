package net.wojteksz128.authservice.service.clientapp;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;

@SuppressWarnings("WeakerAccess")
@Data
public class CreateClientAppDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String clientId;

    @NotEmpty
    private String clientSecret;

    @NotEmpty
    private String webServerRedirectUri;

    private boolean fullNameRequired;

    private boolean birthDateRequired;

    private boolean emailRequired;

    private boolean contactRequired;

    private String description;
}