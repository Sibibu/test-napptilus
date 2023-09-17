package com.shendelzare.napptilus.application.services;

import com.shendelzare.napptilus.adapters.persistence.PriceRepository;
import com.shendelzare.napptilus.application.domain.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class PriceServiceImplTest {

    public static final long PRODUCT_ID = 123L;
    public static final long BRAND_ID = 1L;
    public static final LocalDateTime DATE_TIME = LocalDateTime.now();
    private PriceServiceImpl priceService;

    @Mock
    private PriceRepository priceRepository;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        priceService = new PriceServiceImpl(priceRepository);
    }

    @Test
    public void givenExistingDataReturnPrice() {
        //Given
        Price price = buildPrice();

        //When
        Mockito.when(priceRepository.findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(Mockito.any(), Mockito.any(), Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(Optional.of(price));

        //Then
        Optional<Price> result = priceService.findPriceByDateTimeAndProductAndBrand(DATE_TIME, PRODUCT_ID, BRAND_ID);

        assertEquals(Optional.of(price), result);
    }

    @Test
    public void givenNonExistingDataReturnOptionalEmpty() {
        //Given
        Price price = buildPrice();

        //When
        Mockito.when(priceRepository.findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(Mockito.any(), Mockito.any(), Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(Optional.empty());

        //Then
        Optional<Price> result = priceService.findPriceByDateTimeAndProductAndBrand(DATE_TIME, PRODUCT_ID, BRAND_ID);

        assertEquals(Optional.empty(), result);
    }

    private Price buildPrice() {
        Price price = new Price();
        price.setProductId(PRODUCT_ID);
        price.setBrandId(BRAND_ID);
        price.setPriceList(456L);
        price.setStartDate(DATE_TIME.minusDays(1));
        price.setEndDate(DATE_TIME.plusDays(7));
        price.setPrice(99.99);
        price.setCurr("EUR");
        return price;
    }
}
