package com.shendelzare.napptilus.application.services;

import com.shendelzare.napptilus.adapters.persistence.PriceRepository;
import com.shendelzare.napptilus.application.domain.Price;
import com.shendelzare.napptilus.application.ports.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public Optional<Price> findPriceByDateTimeAndProductAndBrand(final LocalDateTime dateTime, final Long productId, final Long brandId) {
        return priceRepository.findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(dateTime, dateTime, productId, brandId);

    }
}

