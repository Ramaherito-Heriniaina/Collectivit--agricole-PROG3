package com.collectivite.Agricole.repository;

import com.collectivite.Agricole.model.MembershipFee;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ContributionRepository {
    private final Map<Long, MembershipFee> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public MembershipFee save(MembershipFee contribution) {
        if (contribution.getId() == null) {
            contribution.setId(String.valueOf(idGenerator.getAndIncrement()));
        }
        storage.put(Long.valueOf(contribution.getId()), contribution);
        return contribution;
    }

    public Optional<MembershipFee> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<MembershipFee> findByCollectivityId(String collectivityId) {
        return storage.values().stream()
                .filter(c -> c.getCollectivityId().equals(collectivityId))
                .collect(Collectors.toList());
    }

    public List<MembershipFee> findAll() {
        return new ArrayList<>(storage.values());
    }
}