package dev.julioperez.fraud.service;

import dev.julioperez.fraud.dto.FraudResponse;
import dev.julioperez.fraud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class FraudServiceImplementation implements FraudService{
    private final PaymentService paymentService;

    public FraudServiceImplementation(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public FraudResponse validateFraudByUserId(String userId) {
        List<Payment> paymentsByUserId = paymentService.getPaymentsByUserId(userId);
        boolean isNewUser = validateIsANewUserByCreationDate(paymentsByUserId);
        int quantityRejectedByOneDay = determinateQuantityRejectedByOneDay(paymentsByUserId);
        double totalAccumulatedByOneWeek = determinateTotalAccumulatedByOneWeek(paymentsByUserId);
        log.info("validation fraud has been complete by userId {}", userId);
        return new FraudResponse(isNewUser, quantityRejectedByOneDay, totalAccumulatedByOneWeek);
    }

    private boolean validateIsANewUserByCreationDate(List<Payment> payments){
        Payment payment = payments.get(0);
        return payment.getUserCreationAt().after(Date.from(Instant.now()));
    }
    private int determinateQuantityRejectedByOneDay(List<Payment> payments){
        return Long.bitCount(payments
                .stream()
                .filter(particularPayment -> this.determinateDateByDaysBefore(particularPayment.getPaymentDate(), 1))
                .map(Payment::getPaymentStatus)
                .filter(particular -> particular.equals("REJECTED"))
                .count());
    }
    private boolean determinateDateByDaysBefore(Date dateToFilter, int daysBefore) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -daysBefore);
        Date conditionDate = calendar.getTime();
        return conditionDate.before(dateToFilter);
    }
    private double determinateTotalAccumulatedByOneWeek(List<Payment> payments){
        return Double.longBitsToDouble(payments
                .stream()
                .filter(particularPayment -> this.determinateDateByDaysBefore(particularPayment.getPaymentDate(), 7))
                .map(particular -> convertLocalExchangeToDollar(particular.getLocalExchange(), particular.getTotalLocalExchange()))
                .count());

    }
    private Double convertLocalExchangeToDollar(String localExchange, Double totalLocalExchange){
        //call to API
        return null;
    }
}
