package com.collectivite.Agricole.controller;


import com.collectivite.Agricole.exception.BusinessException;
import com.collectivite.Agricole.model.Collectivity;
import com.collectivite.Agricole.service.CollectivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collectivities")
public class CollectivityController {

    @Autowired
    private CollectivityService collectivityService;

    @PostMapping
    public ResponseEntity<?> createCollectivity(@RequestBody Collectivity collectivity) {
        try {
            Collectivity created = collectivityService.createCollectivity(collectivity);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (BusinessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
