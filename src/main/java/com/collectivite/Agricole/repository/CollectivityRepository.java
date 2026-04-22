package com.collectivite.Agricole.repository;

import com.collectivite.Agricole.model.Collectivity;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CollectivityRepository {
    private final Map<String, Collectivity> storage = new ConcurrentHashMap<>();

    public Collectivity save(Collectivity collectivity) {
        storage.put(collectivity.getId(), collectivity);
        return collectivity;
    }

    public Collectivity findById(String id) {
        return storage.get(id);
    }

    public boolean existsByNumero(String numero) {
        return storage.values().stream().anyMatch(c -> numero.equals(c.getNumero()));
    }

    public boolean existsByNom(String nom) {
        return storage.values().stream().anyMatch(c -> nom.equals(c.getNom()));
    }
}