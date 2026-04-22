package com.collectivite.Agricole.repository;

import com.collectivite.Agricole.model.MembershipFee;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MembershipFeeRepository {
    private final Map<String, MembershipFee> storage = new ConcurrentHashMap<>();

    public MembershipFee save(MembershipFee fee) {
        if (fee.getId() == null) {
            fee.setId(UUID.randomUUID().toString());
        }
        storage.put(fee.getId(), fee);
        return fee;
    }

    public Optional<MembershipFee> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<MembershipFee> findByCollectivityId(String collectivityId) {
        return storage.values().stream()
                .filter(f -> f.getCollectivityId().equals(collectivityId))
                .toList();
    }
}