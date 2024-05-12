package com.ms.motorsphere_api.services.impl;

import com.ms.motorsphere_api.model.dao.BidderDAO;
import com.ms.motorsphere_api.model.dao.LoginDAO;
import com.ms.motorsphere_api.model.dao.UsuarioDAO;
import com.ms.motorsphere_api.model.dto.BidderDTO;
import com.ms.motorsphere_api.model.entity.Bidder;
import com.ms.motorsphere_api.services.IBidder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BidderImpl implements IBidder {
    static final Logger log = LoggerFactory.getLogger(BidderImpl.class);

    @Autowired
    private BidderDAO bidderDAO;
    @Autowired
    private UsuarioDAO usuarioDAO;
    @Autowired
    private LoginDAO loginDAO;

    @Override
    public List<BidderDTO> findAll() {

        List<BidderDTO> bidderDTOS = new ArrayList<>();
        List<Bidder> bidder = (List<Bidder>) bidderDAO.findAll();

        for (Bidder bid:
             bidder) {
            bidderDTOS.add(BidderDTO.builder()
                            .bidderId(bid.getId())
                            .userId(bid.getUsuario().getId())
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
                .userId(value.getUsuario().getId())
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



    @Transactional
    public BidderDTO add(BidderDTO bidderDTO) {

        Bidder bidder = Bidder.builder()

                .build();

        bidderDAO.save(bidder);
        return null;
    }

    @Override
    @Transactional
    public BidderDTO update(BidderDTO bidderDTO) {
        // Obtener el bidder existente
        Optional<Bidder> optionalBidder = bidderDAO.findById(bidderDTO.getBidderId());

        // Verificar si el bidder existe en la base de datos
        if (optionalBidder.isPresent()) {
            Bidder bidder = optionalBidder.get();

            // Actualizar los campos necesarios
            bidder.setCreationDate(bidderDTO.getCreationDate());
            bidder.setChecker(bidderDTO.isChecker());

            // Guardar los cambios en la base de datos
            Bidder bidder1 = bidderDAO.save(bidder);

            return BidderDTO.builder()
                    .bidderId(bidder1.getId())
                    .userId(bidder1.getUsuario().getId())
                    .creationDate(bidder1.getCreationDate())
                    .checker(bidder1.isChecker())
                    .build();
        } else {
            return null;
        }
    }
}
