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
    private String description;
    private LocalDateTime createDate;
}
