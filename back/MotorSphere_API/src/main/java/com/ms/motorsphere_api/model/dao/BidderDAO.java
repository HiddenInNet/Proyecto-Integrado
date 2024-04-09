package com.ms.motorsphere_api.model.dao;

import com.ms.motorsphere_api.model.entity.Bidder;
import org.springframework.data.repository.CrudRepository;

public interface BidderDAO extends CrudRepository<Bidder, Long> {
}
