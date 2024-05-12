package com.ms.motorsphere_api.controller;

import com.ms.motorsphere_api.error.ErrorResponse;
import com.ms.motorsphere_api.model.dto.BidderDTO;
import com.ms.motorsphere_api.services.IBidder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v0/bidder")
public class BidderController {
    static final Logger log = LoggerFactory.getLogger(BidderController.class);

    /**
     * dependencia insertada
     * <p>
     * Contiene:
     * <ul>
     *     <li><pre>{@link #bidderService}</pre></li>
     *     <li>getBidderById(Long id)</li>
     * </ul>
     * </p>
     */
    @Autowired
    private IBidder bidderService;

    /**
     * <h3>Get all bidders</h3>
     * @return List of bidders
     * <p>
     * hola mundo esto es {@link #bidderService}
     * </p>
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllBidders(){
        Map<String, Object> response = new HashMap<>();
        try {
            List<BidderDTO> bidderDTOS = bidderService.findAll();
            return new ResponseEntity<>(bidderDTOS, HttpStatus.OK);
        } catch (DataAccessException exRv) {
            response.put("message", exRv.getMessage());
            response.put("bidderDTO list", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getBidderById(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();

        BidderDTO bidderDTO = bidderService.findById(id);
        if (bidderDTO != null){
            return new ResponseEntity<>(bidderDTO, HttpStatus.OK);
        }
        response.put("message", ErrorResponse.SERVER_201+id);
        response.put("bidderDTO", null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("add")
    public ResponseEntity<?> addBidder(@RequestBody BidderDTO bidder){
        Map<String, Object> response = new HashMap<>();

        if (bidder != null) {
            log.info("dentro del if");
            BidderDTO bidderDTO = bidderService.add(bidder);
            log.info("guardado");
            return new ResponseEntity<>(bidderDTO, HttpStatus.CREATED);
        }
        response.put("message", ErrorResponse.SERVER_202);
        response.put("bidderDTO", null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("update")
    public ResponseEntity<?> updateBidder(@RequestBody BidderDTO bidder){
        Map<String, Object> response = new HashMap<>();

        if (bidder != null) {
            log.info("dentro del if");
            BidderDTO bidderDTO = bidderService.update(bidder);
            log.info("guardado");
            return new ResponseEntity<>(bidderDTO, HttpStatus.CREATED);
        }
        response.put("message", ErrorResponse.SERVER_202);
        response.put("bidderDTO", null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
