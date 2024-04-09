package com.ms.motorsphere_api.services;

import com.ms.motorsphere_api.model.dto.BidderDTO;

import java.util.List;

public interface IBidder {

    List<BidderDTO> findAll();
    BidderDTO findById(Long id);
    Boolean remove(Long id);
    BidderDTO save(BidderDTO bidderDTO);
}
