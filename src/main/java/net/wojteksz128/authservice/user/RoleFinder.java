package net.wojteksz128.authservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleFinder {

    private final RoleRepository roleRepository;

    private final RoleToDtoConverter roleToDtoConverter;

    @Autowired
    public RoleFinder(RoleRepository roleRepository, RoleToDtoConverter roleToDtoConverter) {
        this.roleRepository = roleRepository;
        this.roleToDtoConverter = roleToDtoConverter;
    }

    public Optional<RoleDto> findByCode(String code) {
        return Optional.of(roleToDtoConverter.convert(roleRepository.findByCode(code).orElse(null)));
    }
}
