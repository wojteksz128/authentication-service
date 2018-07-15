package net.wojteksz128.authservice.service.clientapp;

import lombok.Data;
import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsDto;

import javax.validation.Valid;
import java.time.LocalDateTime;

@SuppressWarnings("WeakerAccess")
@Data
public class ClientAppDto {

    private Long id;
    @Valid
    private OAuthClientDetailsDto clientDetailsDto;
    private String name;
    private String description;
    private LocalDateTime createDate;
    private Boolean fullNameRequired;
    private Boolean birthDateRequired;
    private Boolean emailRequired;
    private Boolean contactRequired;
}
