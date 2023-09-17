package com.shendelzare.napptilus.adapters.persistence;


import com.shendelzare.napptilus.application.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    Optional<Price> findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
            LocalDateTime startDateTime,
            LocalDateTime endDateTime,
            Long productId,
            Long brandId
    );
}


