package net.wojteksz128.authservice.user.impl;

import net.wojteksz128.authservice.user.RoleDto;
import net.wojteksz128.authservice.user.RoleFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class RoleFinderImpl implements RoleFinder {

    private final RoleRepository roleRepository;

    private final RoleToDtoConverter roleToDtoConverter;

    @Autowired
    public RoleFinderImpl(RoleRepository roleRepository, RoleToDtoConverter roleToDtoConverter) {
        this.roleRepository = roleRepository;
        this.roleToDtoConverter = roleToDtoConverter;
    }

    @Override
    public Optional<RoleDto> findByCode(String code) {
        return Optional.of(roleToDtoConverter.convert(roleRepository.findByCode(code).orElse(null)));
    }
}
