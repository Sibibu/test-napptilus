package com.shendelzare.napptilus.adapters.controller;

import com.shendelzare.napptilus.adapters.rest.model.PriceRequest;
import com.shendelzare.napptilus.adapters.rest.model.PriceResponse;
import com.shendelzare.napptilus.application.domain.Price;
import com.shendelzare.napptilus.application.mappers.PriceMapper;
import com.shendelzare.napptilus.application.ports.PriceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceService priceService;
    private final PriceMapper priceMapper;

    @GetMapping
    public ResponseEntity<PriceResponse> getPrice(@Valid final PriceRequest priceRequest) {

        Optional<Price> price = priceService.findPriceByDateTimeAndProductAndBrand(priceRequest.getDateTime(), priceRequest.getProductId(), priceRequest.getBrandId());

        if (price.isPresent()) {
            PriceResponse priceResponse = priceMapper.toResponse(price.get());
            return ResponseEntity.ok(priceResponse);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
