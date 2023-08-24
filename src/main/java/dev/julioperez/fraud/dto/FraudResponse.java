package dev.julioperez.fraud.dto;

public record FraudResponse (boolean is_new_user, int qty_rejected_1d, double total_amt_7d){
}
