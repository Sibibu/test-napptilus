package com.shendelzare.napptilus.application.mappers;

import com.shendelzare.napptilus.adapters.rest.model.PriceResponse;
import com.shendelzare.napptilus.application.domain.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceMapperImplTest {

    private PriceMapper priceMapper;

    @BeforeEach
    public void setUp() {
        priceMapper = new PriceMapperImpl();
    }

    @Test
    public void givenPriceReturnValidDTOPriceResponse() {
        Price price = new Price();
        price.setProductId(123L);
        price.setBrandId(1L);
        price.setPriceList(456L);
        price.setStartDate(LocalDateTime.now());
        price.setEndDate(LocalDateTime.now().plusDays(7));
        price.setPrice(99.99);
        price.setCurr("EUR");

        PriceResponse response = priceMapper.toResponse(price);

        assertEquals(price.getProductId(), response.getProductId());
        assertEquals(price.getBrandId(), response.getBrandId());
        assertEquals(price.getPriceList(), response.getPriceList());
        assertEquals(price.getStartDate(), response.getStartDate());
        assertEquals(price.getEndDate(), response.getEndDate());
        assertEquals(price.getPrice(), response.getPrice());
        assertEquals(price.getCurr(), response.getCurr());
    }
}