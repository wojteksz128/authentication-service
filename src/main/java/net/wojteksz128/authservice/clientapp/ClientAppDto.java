package net.wojteksz128.authservice.clientapp;

import lombok.Data;

@SuppressWarnings("WeakerAccess")
@Data
public class ClientAppDto {

    private String guid;
    private String name;
    private String description;
}
