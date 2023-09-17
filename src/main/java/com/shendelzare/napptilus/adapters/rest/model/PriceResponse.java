package com.shendelzare.napptilus.adapters.rest.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PriceResponse {

    private Long productId;
    private Long brandId;
    private Long priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;
    private String curr;

}

