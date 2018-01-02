package net.wojteksz128.authservice.clientapp;

import lombok.Data;

@SuppressWarnings("WeakerAccess")
@Data
public class CreateClientAppDto {

    private String name;
    private String description;
}