package com.ocs.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@ToString
public class Subscription {
    private UUID id;
    private UUID customerId;
    private UUID productId;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;
}
