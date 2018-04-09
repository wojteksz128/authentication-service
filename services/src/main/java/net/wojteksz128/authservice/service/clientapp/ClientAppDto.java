package net.wojteksz128.authservice.service.clientapp;

import lombok.Data;

import java.time.LocalDateTime;

@SuppressWarnings("WeakerAccess")
@Data
public class ClientAppDto {

    private Long id;
    private OAuthClientDetailsDto clientDetailsDto;
    private String description;
    private LocalDateTime createDate;
}
