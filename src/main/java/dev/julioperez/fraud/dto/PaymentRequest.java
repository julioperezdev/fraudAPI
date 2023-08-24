package dev.julioperez.fraud.dto;

import java.util.Date;

public record PaymentRequest (
        String id,
        String userId,
        String country,
        String localExchange,
        Double totalLocalExchange,
        Date paymentDate,
        String paymentStatus,
        Date userCreationAt
) {
}
