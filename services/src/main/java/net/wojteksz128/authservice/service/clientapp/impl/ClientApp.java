package net.wojteksz128.authservice.service.clientapp.impl;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ClientApps")
class ClientApp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", unique = true, nullable = false)
    private OAuthClientDetails clientDetails;

    @Column(name = "description")
    private String description;

    @Column(name = "createDate", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "user_id", nullable = false)
    @JoinColumn(table = "Users", foreignKey = @ForeignKey(name = "id"))
    private Long userId;
}
