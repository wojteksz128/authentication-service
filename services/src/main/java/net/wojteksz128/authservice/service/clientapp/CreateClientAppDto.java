package net.wojteksz128.authservice.service.clientapp;

import lombok.Data;

@SuppressWarnings("WeakerAccess")
@Data
public class CreateClientAppDto {

    private OAuthClientDetailsDto clientDetailsDto;
    private String description;
}