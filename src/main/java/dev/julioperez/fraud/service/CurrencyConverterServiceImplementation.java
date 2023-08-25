package dev.julioperez.fraud.service;

import dev.julioperez.fraud.dto.ConvertApiResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class CurrencyConverterServiceImplementation implements CurrencyConverterService{

    @Override
    public double convertToUSD(String localExchange, Double totalLocalExchange) {
        ResponseEntity<ConvertApiResponse> convertResponseEntity =
                instanceRestTemplate().exchange(
                getUrlToConvertCurrencyToUSD(localExchange,totalLocalExchange),
                HttpMethod.GET,
                getHeadersWithCredentials(), ConvertApiResponse.class);

        return Objects.requireNonNull(
                convertResponseEntity.getBody())
                .to_amount;
    }

    private String getUrlToConvertCurrencyToUSD(String localExchange, Double totalLocalExchange){
        final String CONVERTER_API_URL = "https://currencyconverter.p.rapidapi.com/availablec";
        final String INITIALIZE_URL_PARAMETER = "?";
        final String NEXT_URL_PARAMETER_SYMBOL = "&";

        return CONVERTER_API_URL
                .concat(INITIALIZE_URL_PARAMETER)
                .concat("from_amount =")
                .concat("1")//totalLocalExchange
                .concat(NEXT_URL_PARAMETER_SYMBOL)
                .concat("from =")
                .concat("BRL")//localExchange
                .concat(NEXT_URL_PARAMETER_SYMBOL)
                .concat("to = USD");
    }
    private RestTemplate instanceRestTemplate(){
        return new RestTemplate();
    }

    private HttpEntity<HttpHeaders> getHeadersWithCredentials() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("X-RapidAPI-Key","SIGN-UP-FOR-KEY");
        httpHeaders.set("X-RapidAPI-Host","currencyconverter.p.rapidapi.com");
        return new HttpEntity<>(httpHeaders);
    }
}
