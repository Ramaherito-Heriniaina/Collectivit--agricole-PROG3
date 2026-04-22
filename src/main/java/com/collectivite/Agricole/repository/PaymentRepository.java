package com.collectivite.Agricole.repository;

import com.collectivite.Agricole.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class PaymentRepository {
    private final Map<Long, Payment> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Payment save(Payment payment) {
        if (payment.getId() == null) {
            payment.setId(idGenerator.getAndIncrement());
        }
        storage.put(payment.getId(), payment);
        return payment;
    }

    public Optional<Payment> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<Payment> findByCollectivityId(String collectivityId) {
        return storage.values().stream()
                .filter(p -> p.getCollectivityId().equals(collectivityId))
                .collect(Collectors.toList());
    }

    public List<Payment> findByMemberId(String memberId) {
        return storage.values().stream()
                .filter(p -> p.getMemberId().equals(memberId))
                .collect(Collectors.toList());
    }

    public List<Payment> findAll() {
        return new ArrayList<>(storage.values());
    }
}