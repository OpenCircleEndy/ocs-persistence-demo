package com.ocs.demo.model;


import lombok.*;

import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@ToString
public class Product {
    private UUID id;
    private String code;
    private String status;
    @Singular
    private Set<Subscription> subscriptions;
}
