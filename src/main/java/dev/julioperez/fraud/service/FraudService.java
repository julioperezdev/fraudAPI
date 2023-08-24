package dev.julioperez.fraud.service;

import dev.julioperez.fraud.dto.FraudResponse;
import dev.julioperez.fraud.dto.PaymentRequest;

public interface FraudService {
    FraudResponse validateFraudByUserId(String userId);
}
