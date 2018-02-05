package net.wojteksz128.authservice.user;

import java.util.Optional;

public interface RoleFinder {
    Optional<RoleDto> findByCode(String code);
}
