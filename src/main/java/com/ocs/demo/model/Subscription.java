package com.ocs.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@ToString
public class Subscription {
    private UUID id;
    private UUID customerId;
    private UUID productId;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;
}
