package dev.julioperez.fraud.controller;

import dev.julioperez.fraud.dto.FraudResponse;
import dev.julioperez.fraud.dto.PaymentRequest;
import dev.julioperez.fraud.service.FraudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class FraudController {
    private final FraudService fraudService;

    public FraudController(FraudService fraudService) {
        this.fraudService = fraudService;
    }

    @PostMapping("/validate/{userId}")
    public ResponseEntity<FraudResponse> validateFraudByPayment(@PathVariable String userId){
        FraudResponse fraudResponse = fraudService.validateFraudByUserId(userId);
        return new ResponseEntity<>(fraudResponse, HttpStatus.OK);
    }
}
