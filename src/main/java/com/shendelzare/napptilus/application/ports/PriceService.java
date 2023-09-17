package com.shendelzare.napptilus.application.ports;

import com.shendelzare.napptilus.application.domain.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {
    Optional<Price> findPriceByDateTimeAndProductAndBrand(
            LocalDateTime dateTime,
            Long productId,
            Long brandId
    );
}
