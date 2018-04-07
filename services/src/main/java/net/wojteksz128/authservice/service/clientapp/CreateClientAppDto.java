package net.wojteksz128.authservice.service.clientapp;

import lombok.Data;
import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsDto;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("WeakerAccess")
@Data
public class CreateClientAppDto {

    @NotEmpty
    private OAuthClientDetailsDto clientDetailsDto;

    private String description;
}