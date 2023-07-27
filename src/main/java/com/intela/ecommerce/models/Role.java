package com.intela.ecommerce.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.intela.ecommerce.models.Permission.*;

@RequiredArgsConstructor
public enum Role {
    CUSTOMER(
            Set.of(
                  CUSTOMER_READ,
                    CUSTOMER_CREATE,
                    CUSTOMER_UPDATE,
                    CUSTOMER_DELETE
            )
    ),
    SHOPKEEPER(
            Set.of(
                    SHOPKEEPER_CREATE,
                    SHOPKEEPER_DELETE,
                    SHOPKEEPER_UPDATE,
                    SHOPKEEPER_READ
            )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
