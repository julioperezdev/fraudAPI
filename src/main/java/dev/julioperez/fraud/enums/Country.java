package dev.julioperez.fraud.enums;

import java.util.Arrays;
import java.util.List;

public enum Country {
    ARGENTINA("ARGENTINA", "ARS"),
    BRAZIL("BRAZIL", "REL"),
    BOLIVIA("BOLIVIA", "PES");

    Country(String name, String localExchange){}

    public static Country returnCountryByName(String countryName){
        List<Country> countries = Arrays.stream(Country.values()).filter(particular -> particular.name().equals(countryName)).toList();
        if(countries.isEmpty()) throw new IllegalArgumentException(String.format("%s value dont exist as Country values", countryName));
        if(countries.size() != 1) throw new IllegalArgumentException(String.format("%s value should be return an only Country", countryName));
        return countries.get(0);
    }

}
