package com.collectivite.Agricole.repository;


import com.collectivite.Agricole.model.Collectivity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CollectivityRepository {
    private final Map<Long, Collectivity> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Collectivity save(Collectivity collectivity) {
        if (collectivity.getId() == null) {
            collectivity.setId(idGenerator.getAndIncrement());
        }
        storage.put(collectivity.getId(), collectivity);
        return collectivity;
    }

    public List<Collectivity> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Collectivity findById(Long id) {
        return storage.get(id);
    }
}