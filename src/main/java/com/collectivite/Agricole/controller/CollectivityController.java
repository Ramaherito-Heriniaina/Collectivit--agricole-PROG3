package com.collectivite.Agricole.controller;

import com.collectivite.Agricole.exception.BusinessException;
import com.collectivite.Agricole.model.Collectivity;
import com.collectivite.Agricole.service.CollectivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Map;

@RestController
@RequestMapping("/collectivities")
public class CollectivityController {

    @Autowired
    private CollectivityService collectivityService;

    // Endpoint existant POST /collectivities
    @PostMapping
    public ResponseEntity<?> createCollectivity(@RequestBody List collectivities) {
        // ... (code inchangé)
        return null;
    }

    // Nouvel endpoint PUT /collectivities/{id}/identifiers
    @PutMapping("/{id}/identifiers")
    public ResponseEntity<?> assignIdentifiers(
            @PathVariable String id,
            @RequestBody Map<String, String> payload) {
        String numero = payload.get("numero");
        String nom = payload.get("nom");
        if (numero == null || nom == null) {
            return ResponseEntity.badRequest().body("Les champs 'numero' et 'nom' sont requis");
        }
        try {
            Collectivity updated = collectivityService.assignIdentifiers(id, numero, nom);
            return ResponseEntity.ok(updated);
        } catch (BusinessException e) {
            String message = e.getMessage();
            if (message.contains("existe déjà")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
            } else if (message.contains("déjà attribués")) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(message);
            } else if (message.contains("introuvable")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }
            return ResponseEntity.badRequest().body(message);
        }
    }
}