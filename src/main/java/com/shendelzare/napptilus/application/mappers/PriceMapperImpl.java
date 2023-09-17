package com.shendelzare.napptilus.application.mappers;


import com.shendelzare.napptilus.adapters.rest.model.PriceResponse;
import com.shendelzare.napptilus.application.domain.Price;
import org.springframework.stereotype.Component;

@Component
public class PriceMapperImpl implements PriceMapper {
    public PriceResponse toResponse(Price price) {
        PriceResponse dto = new PriceResponse();
        dto.setProductId(price.getProductId());
        dto.setBrandId(price.getBrandId());
        dto.setPriceList(price.getPriceList());
        dto.setStartDate(price.getStartDate());
        dto.setEndDate(price.getEndDate());
        dto.setPrice(price.getPrice());
        dto.setCurr(price.getCurr());
        return dto;
    }
}
