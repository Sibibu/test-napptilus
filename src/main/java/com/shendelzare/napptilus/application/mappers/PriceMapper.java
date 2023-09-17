package com.shendelzare.napptilus.application.mappers;

import com.shendelzare.napptilus.adapters.rest.model.PriceResponse;
import com.shendelzare.napptilus.application.domain.Price;

public interface PriceMapper {

    PriceResponse toResponse(Price price);
}
