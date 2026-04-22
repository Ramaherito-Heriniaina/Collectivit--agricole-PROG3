package com.collectivite.Agricole.service;

import com.collectivite.Agricole.exception.BusinessException;
import com.collectivite.Agricole.model.Collectivity;
import com.collectivite.Agricole.repository.CollectivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class CollectivityService {

    @Autowired
    private CollectivityRepository collectivityRepository;

    // Méthode existante pour créer une collectivité (A)
    public Collectivity createCollectivity(Collectivity collectivity) {
        // ... (code inchangé, avec validation des membres, etc.)
        collectivity.setCreationDate(LocalDate.now());
        collectivity.setAuthorized(false);
        return collectivityRepository.save(collectivity);
    }

    // Nouvelle méthode pour la fonctionnalité J
    public Collectivity assignIdentifiers(String id, String numero, String nom) {
        Collectivity collectivity = collectivityRepository.findById(id);
        if (collectivity == null) {
            throw new BusinessException("Collectivité introuvable");
        }
        // Vérifier si déjà attribués
        if (collectivity.getNumero() != null || collectivity.getNom() != null) {
            throw new BusinessException("Les identifiants sont déjà attribués et ne peuvent pas être modifiés");
        }
        // Vérifier l'unicité
        if (collectivityRepository.existsByNumero(numero)) {
            throw new BusinessException("Le numéro existe déjà");
        }
        if (collectivityRepository.existsByNom(nom)) {
            throw new BusinessException("Le nom existe déjà");
        }
        collectivity.setNumero(numero);
        collectivity.setNom(nom);
        return collectivityRepository.save(collectivity);
    }
}