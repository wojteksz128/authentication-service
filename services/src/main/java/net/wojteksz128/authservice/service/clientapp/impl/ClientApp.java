package net.wojteksz128.authservice.service.clientapp.impl;

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

    @Column(name = "client_id", unique = true, nullable = false)
    @JoinColumn(table = "oauth_client_details", foreignKey = @ForeignKey(name = "client_id"))
    private String clientId;

    @Column(name = "description")
    private String description;

    @Column(name = "createDate", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "user_id", nullable = false)
    @JoinColumn(table = "Users", foreignKey = @ForeignKey(name = "id"))
    private Long userId;
}
