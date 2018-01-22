package net.wojteksz128.authservice.clientapp;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("WeakerAccess")
@Data
public class CreateClientAppDto {

    @NotEmpty
    private String name;
    private String description;
}