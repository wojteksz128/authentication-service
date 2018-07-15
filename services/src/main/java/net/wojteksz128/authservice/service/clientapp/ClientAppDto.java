package net.wojteksz128.authservice.service.clientapp;

import lombok.Data;
import net.wojteksz128.authservice.service.oauth.OAuthClientDetailsDto;

import java.time.LocalDateTime;

@SuppressWarnings("WeakerAccess")
@Data
public class ClientAppDto {

    private Long id;
    private OAuthClientDetailsDto clientDetailsDto;
    private String name;
    private String description;
    private LocalDateTime createDate;
}
