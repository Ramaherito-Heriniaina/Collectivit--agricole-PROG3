package com.collectivite.Agricole.repository;

import com.collectivite.Agricole.model.Contribution;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ContributionRepository {
    private final Map<Long, Contribution> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Contribution save(Contribution contribution) {
        if (contribution.getId() == null) {
            contribution.setId(idGenerator.getAndIncrement());
        }
        storage.put(contribution.getId(), contribution);
        return contribution;
    }

    public Optional<Contribution> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<Contribution> findByCollectivityId(String collectivityId) {
        return storage.values().stream()
                .filter(c -> c.getCollectivityId().equals(collectivityId))
                .collect(Collectors.toList());
    }

    public List<Contribution> findAll() {
        return new ArrayList<>(storage.values());
    }
}