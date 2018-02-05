package net.wojteksz128.authservice.user.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByCode(String code);
}
