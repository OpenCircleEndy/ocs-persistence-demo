package com.ocs.demo.model;


import lombok.*;

import java.beans.ConstructorProperties;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@Builder(toBuilder = true)
@AllArgsConstructor
@Getter
@ToString
public class Product {
    private UUID id;
    private String code;
    private String status;

    @Singular
    private Set<Subscription> subscriptions = Collections.emptySet();

    @ConstructorProperties({ "ID", "CODE", "STATUS" })
    public Product(UUID id, String code, String status) {
        this.id = id;
        this.code = code;
        this.status = status;
    }
}
