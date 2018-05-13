package net.wojteksz128.authservice.service.user.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {


    private transient final User user;
    private final String username;
    private final Set<Role> roles;
    private final Long id;

    UserDetailsImpl(final User user) {
        this.user = user;
        this.username = user.getEmail();
        this.roles = user.getRoles();
        this.id = user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getCode()))
            .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.user != null ? this.user.getPassword() : null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }
}
