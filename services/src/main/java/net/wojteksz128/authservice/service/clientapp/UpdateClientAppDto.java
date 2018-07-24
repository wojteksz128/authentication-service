package net.wojteksz128.authservice.service.clientapp;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;

@Data
public class UpdateClientAppDto {

    @NotEmpty
    private String name;
    private LocalDateTime createDate;
    @NotEmpty
    private String clientId;
    @NotEmpty
    private String clientSecret;
    @NotEmpty
    private String webServerRedirectUri;
    private String description;
    private Boolean fullNameRequired;
    private Boolean birthDateRequired;
    private Boolean emailRequired;
    private Boolean contactRequired;
}
