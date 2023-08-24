package dev.julioperez.fraud.service;

import dev.julioperez.fraud.dto.PaymentRequest;
import dev.julioperez.fraud.entity.Payment;
import dev.julioperez.fraud.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PaymentServiceImplementation implements PaymentService{
    private final PaymentRepository paymentRepository;

    public PaymentServiceImplementation(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public boolean savePayments(List<PaymentRequest> paymentRequests){
        try{
            List<Payment> payments = this.paymentListModelMapper(paymentRequests);
            paymentRepository.saveAll(payments);
            return true;
        }catch (Exception exception){
            log.error("savePayments method has an error", exception);
            return false;
        }
    }

    @Override
    public List<Payment> getPaymentsByUserId(String userId) {
        return paymentRepository.getPaymentsByUserId(userId);
    }
    private List<Payment> paymentListModelMapper(List<PaymentRequest> paymentRequests){
        return paymentRequests.stream().map(this::paymentModelMapper).toList();
    }
    private Payment paymentModelMapper(PaymentRequest paymentRequest){
        return new Payment(
                paymentRequest.id(),
                paymentRequest.userId(),
                paymentRequest.userCreationAt(),
                paymentRequest.country(),
                paymentRequest.localExchange(),
                paymentRequest.totalLocalExchange(),
                paymentRequest.paymentDate(),
                paymentRequest.paymentStatus());
    }
}
