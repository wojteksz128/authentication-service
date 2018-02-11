package net.wojteksz128.authservice.service.clientapp;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;

@SuppressWarnings("WeakerAccess")
@Data
public class ClientAppDto {
    @NotEmpty
    private String guid;

    @NotEmpty
    private String name;

    private String description;

    private LocalDateTime createDate;
}
