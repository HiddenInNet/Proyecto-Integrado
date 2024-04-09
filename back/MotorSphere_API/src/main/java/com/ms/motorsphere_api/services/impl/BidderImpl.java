package com.ms.motorsphere_api.services.impl;

import com.ms.motorsphere_api.controller.UserController;
import com.ms.motorsphere_api.model.dao.BidderDAO;
import com.ms.motorsphere_api.model.dao.UserDAO;
import com.ms.motorsphere_api.model.dto.BidderDTO;
import com.ms.motorsphere_api.model.entity.Bidder;
import com.ms.motorsphere_api.model.entity.User;
import com.ms.motorsphere_api.services.IBidder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BidderImpl implements IBidder {
    static final Logger log = LoggerFactory.getLogger(BidderImpl.class);

    @Autowired
    private BidderDAO bidderDAO;
    @Autowired
    private UserDAO userDAO;

    @Override
    public List<BidderDTO> findAll() {

        List<BidderDTO> bidderDTOS = new ArrayList<>();
        List<Bidder> bidder = (List<Bidder>) bidderDAO.findAll();

        for (Bidder bid:
             bidder) {
            bidderDTOS.add(BidderDTO.builder()
                            .bidderId(bid.getId())
                            .userId(bid.getUser().getId())
                            .checker(bid.isChecker())
                            .creationDate(bid.getCreationDate())
                    .build());
        }
        log.info(bidderDTOS.toString());
        return bidderDTOS;
    }

    @Override
    public BidderDTO findById(Long id) {
        Optional<Bidder> bidder = bidderDAO.findById(id);

        return bidder.map(value -> BidderDTO.builder()
                .bidderId(value.getId())
                .userId(value.getUser().getId())
                .checker(value.isChecker())
                .creationDate(value.getCreationDate())
                .build()).orElse(null);
    }

    @Override
    public Boolean remove(Long id) {
        if(id != null){
            bidderDAO.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public BidderDTO save(BidderDTO bidderDTO) {
        Bidder bidder = null;
        if(bidderDTO != null && userDAO.findById(bidderDTO.getUserId()).isPresent()){
            log.info("dentro: ");
            Bidder bidderAdd = Bidder.builder()
                    .id(bidderDTO.getBidderId())
                    .user(userDAO.findById(bidderDTO.getUserId()).orElse(null))
                    .creationDate(bidderDTO.getCreationDate())
                    .checker(bidderDTO.isChecker())
                    .build();
            log.info("construido: "+bidderAdd.toString());
            bidder = bidderDAO.save(bidderAdd);

            return BidderDTO.builder()
                    .bidderId(bidder.getId())
                    .userId(bidder.getUser().getId())
                    .creationDate(bidder.getCreationDate())
                    .checker(bidder.isChecker())
                    .build();
        }
        return null;
    }
}
