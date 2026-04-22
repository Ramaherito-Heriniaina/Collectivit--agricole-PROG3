package com.collectivite.Agricole.repository;


import com.collectivite.Agricole.model.Account;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class AccountRepository {
    private final Map<Long, Account> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Account save(Account account) {
        if (account.getId() == null) {
            account.setId(idGenerator.getAndIncrement());
        }
        storage.put(account.getId(), account);
        return account;
    }

    public Optional<Account> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public Object findByEntity(String entityType, String entityId) {
        return storage.values().stream()
                .filter(a -> a.getEntityType().equals(entityType) &&
                        (entityId == null || a.getEntityId().equals(entityId)))
                .collect(Collectors.toList());
    }

    public Optional<Account> findCaisseByEntity(String entityType, String entityId) {
        return storage.values().stream()
                .filter(a -> a.getEntityType().equals(entityType) &&
                        (entityId == null || a.getEntityId().equals(entityId)) &&
                        "CAISSE".equals(a.getAccountType()))
                .findFirst();
    }

    public List<Account> findAll() {
        return new ArrayList<>(storage.values());
    }
}