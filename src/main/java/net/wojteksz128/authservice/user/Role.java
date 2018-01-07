package net.wojteksz128.authservice.user;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Roles")
class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true, nullable = false, updatable = false)
    private String code;
}
