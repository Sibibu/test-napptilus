package com.shendelzare.napptilus.adapters.controller;

import com.shendelzare.napptilus.adapters.rest.model.PriceRequest;
import com.shendelzare.napptilus.adapters.rest.model.PriceResponse;
import com.shendelzare.napptilus.application.domain.Price;
import com.shendelzare.napptilus.application.mappers.PriceMapper;
import com.shendelzare.napptilus.application.ports.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;


public class PriceControllerTest {

    private PriceController priceController;

    @Mock
    private PriceService priceService;

    @Mock
    private PriceMapper priceMapper;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        priceController = new PriceController(priceService, priceMapper);
    }

    @Test
    public void testGetPriceSuccess() {
        //Given
        PriceRequest priceRequest = buildPriceRequest();
        Price price = buildPrice();

        //When
        Mockito.when(priceService.findPriceByDateTimeAndProductAndBrand(Mockito.any(), Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(Optional.of(price));
        Mockito.when(priceMapper.toResponse(Mockito.any())).thenReturn(new PriceResponse());


        ResponseEntity<PriceResponse> responseEntity = priceController.getPrice(priceRequest);

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetPriceNoContent() {
        //Given
        PriceRequest priceRequest = buildPriceRequest();

        //When
        Mockito.when(priceService.findPriceByDateTimeAndProductAndBrand(Mockito.any(), Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(Optional.empty());

        //Then
        ResponseEntity<PriceResponse> responseEntity = priceController.getPrice(priceRequest);

        assertEquals(204, responseEntity.getStatusCodeValue());

    }

    @Test
    public void testGetPriceBadRequest() {
        //Given
        PriceRequest priceRequest = buildPriceRequest();
        priceRequest.setBrandId((long) -5);

        //Then
        ResponseEntity<PriceResponse> responseEntity = priceController.getPrice(priceRequest);

        assertEquals(204, responseEntity.getStatusCodeValue());

    }

    public PriceRequest buildPriceRequest() {
        PriceRequest priceRequest = new PriceRequest();
        priceRequest.setDateTime(LocalDateTime.now());
        priceRequest.setProductId(123L);
        priceRequest.setBrandId(1L);
        return priceRequest;
    }


    private Price buildPrice() {
        Price price = new Price();
        price.setProductId(123L);
        price.setBrandId(1L);
        price.setPriceList(456L);
        price.setStartDate(LocalDateTime.now());
        price.setEndDate(LocalDateTime.now().plusDays(7));
        price.setPrice(99.99);
        price.setCurr("EUR");
        return price;
    }
}
