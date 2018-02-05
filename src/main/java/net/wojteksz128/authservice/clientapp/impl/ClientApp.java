package net.wojteksz128.authservice.clientapp.impl;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ClientApps")
class ClientApp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "guid", unique = true, nullable = false)
    private String guid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "createDate", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
