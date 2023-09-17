package com.shendelzare.napptilus.adapters.controller;

import com.shendelzare.napptilus.application.domain.Price;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/data.sql"})
public class PriceControllerIntegrationTest {
    private static final Long PRODUCT_ID = Long.valueOf(35455);
    private static final Long BRAND_ID = Long.valueOf(1);
    private static final String CURR = "EUR";

    @Autowired
    private MockMvc mockMvc;

    private static Stream<Arguments> providePrices() {
        Price priceTest1 =         Price.builder().productId(PRODUCT_ID).brandId(BRAND_ID).priceList(2L)
                .startDate(LocalDateTime.parse("2020-06-14T06:00:00", DateTimeFormatter.ISO_DATE_TIME))
                .endDate(LocalDateTime.parse("2020-06-14T18:30:00", DateTimeFormatter.ISO_DATE_TIME))
                .price(25.45).curr(CURR)
                .build();
        Price priceTest2 =         Price.builder().productId(PRODUCT_ID).brandId(BRAND_ID).priceList(2L)
                .startDate(LocalDateTime.parse("2020-06-14T06:00:00", DateTimeFormatter.ISO_DATE_TIME))
                .endDate(LocalDateTime.parse("2020-06-14T18:30:00", DateTimeFormatter.ISO_DATE_TIME))
                .price(25.45).curr(CURR)
                .build();
        Price priceTest3 =         Price.builder().productId(PRODUCT_ID).brandId(BRAND_ID).priceList(1L)
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00", DateTimeFormatter.ISO_DATE_TIME))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59", DateTimeFormatter.ISO_DATE_TIME))
                .price(35.5).curr(CURR)
                .build();
        Price priceTest4 =         Price.builder().productId(PRODUCT_ID).brandId(BRAND_ID).priceList(3L)
                .startDate(LocalDateTime.parse("2020-06-15T00:00:00", DateTimeFormatter.ISO_DATE_TIME))
                .endDate(LocalDateTime.parse("2020-06-15T11:00:00", DateTimeFormatter.ISO_DATE_TIME))
                .price(30.5).curr(CURR)
                .build();
        Price priceTest5 =         Price.builder().productId(PRODUCT_ID).brandId(BRAND_ID).priceList(4L)
                .startDate(LocalDateTime.parse("2020-06-15T16:00:00", DateTimeFormatter.ISO_DATE_TIME))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59", DateTimeFormatter.ISO_DATE_TIME))
                .price(38.95).curr(CURR)
                .build();



        return Stream.of(
                Arguments.of(LocalDateTime.parse("2020-06-14T10:00:00", DateTimeFormatter.ISO_DATE_TIME), priceTest1),
                Arguments.of(LocalDateTime.parse("2020-06-14T16:00:00", DateTimeFormatter.ISO_DATE_TIME), priceTest2),
                Arguments.of(LocalDateTime.parse("2020-06-14T21:00:00", DateTimeFormatter.ISO_DATE_TIME), priceTest3),
                Arguments.of(LocalDateTime.parse("2020-06-15T10:00:00", DateTimeFormatter.ISO_DATE_TIME), priceTest4),
                Arguments.of(LocalDateTime.parse("2020-06-16T21:00:00", DateTimeFormatter.ISO_DATE_TIME), priceTest5)
        );
    }
    @ParameterizedTest
    @MethodSource("providePrices")
    public void testGetPriceEndpoint( LocalDateTime dateTime, Price price) throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("dateTime", dateTime.format(DateTimeFormatter.ISO_DATE_TIME))
                        .param("productId", PRODUCT_ID.toString())
                        .param("brandId", BRAND_ID.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productId").value(price.getProductId()))
                .andExpect(jsonPath("$.brandId").value(price.getBrandId()))
                .andExpect(jsonPath("$.priceList").value(price.getPriceList()))
                .andExpect(jsonPath("$.startDate").value(price.getStartDate().format(DateTimeFormatter.ISO_DATE_TIME)))
                .andExpect(jsonPath("$.endDate").value(price.getEndDate().format(DateTimeFormatter.ISO_DATE_TIME)))
                .andExpect(jsonPath("$.price").value(price.getPrice()))
                .andExpect(jsonPath("$.curr").value(price.getCurr()));
    }
}
