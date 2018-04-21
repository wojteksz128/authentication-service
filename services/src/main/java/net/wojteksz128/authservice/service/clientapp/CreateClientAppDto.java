package net.wojteksz128.authservice.service.clientapp;

import lombok.Data;

import javax.validation.Valid;

@SuppressWarnings("WeakerAccess")
@Data
public class CreateClientAppDto {

    @Valid
    private OAuthClientDetailsDto clientDetailsDto;
    private String description;
}