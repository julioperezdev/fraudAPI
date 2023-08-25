package dev.julioperez.fraud.dto;

import lombok.Data;

@Data
public class ConvertApiResponse {
    public String from;
    public String to;
    public int from_amount;
    public double to_amount;
}
