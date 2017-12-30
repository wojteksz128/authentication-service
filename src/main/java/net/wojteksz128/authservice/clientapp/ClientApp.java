package net.wojteksz128.authservice.clientapp;

import lombok.Data;

@Data
class ClientApp {

    private Long id;
    private String guid;
    private String name;
    private String description;
}
