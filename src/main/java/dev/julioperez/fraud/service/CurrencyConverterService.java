package dev.julioperez.fraud.service;

public interface CurrencyConverterService {
    double convertToUSD(String localExchange, Double totalLocalExchange);
}
