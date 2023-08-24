package dev.julioperez.fraud.service;

import dev.julioperez.fraud.dto.PaymentRequest;
import dev.julioperez.fraud.entity.Payment;

import java.util.List;

public interface PaymentService {
    boolean savePayments(List<PaymentRequest> paymentRequests);
    List<Payment> getPaymentsByUserId(String userId);
}
