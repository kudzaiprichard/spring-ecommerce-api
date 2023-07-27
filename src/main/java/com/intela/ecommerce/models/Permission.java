package com.intela.ecommerce.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    CUSTOMER_READ("customer:read"),
    CUSTOMER_DELETE("customer:delete"),
    CUSTOMER_UPDATE("customer:update"),
    CUSTOMER_CREATE("customer:create"),

    SHOPKEEPER_READ("shopkeeper:read"),
    SHOPKEEPER_DELETE("shopkeeper:delete"),
    SHOPKEEPER_UPDATE("shopkeeper:update"),
    SHOPKEEPER_CREATE("shopkeeper:create");

    @Getter
    private final String permission;

}
