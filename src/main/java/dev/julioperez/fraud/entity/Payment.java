package dev.julioperez.fraud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
    @Id
    private String id;
    private String userId;
    private Date userCreationAt;
    private String country;//ENUM
    private String localExchange;//ENUM
    private Double totalLocalExchange;
    private Date paymentDate;
    private String paymentStatus;

}
