package com.collectivite.Agricole.service;

import com.collectivite.Agricole.model.CollectivityTransaction;

import com.collectivite.Agricole.model.CollectivityTransaction;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TransactionService {
    private final Map<String, List<CollectivityTransaction>> storage = new ConcurrentHashMap<>();

    public void saveTransaction(String collectivityId, CollectivityTransaction transaction) {
        storage.computeIfAbsent(collectivityId, k -> new ArrayList<>()).add(transaction);
    }

    public List<CollectivityTransaction> getTransactions(String collectivityId, LocalDate from, LocalDate to) {
        return storage.getOrDefault(collectivityId, Collections.emptyList()).stream()
                .filter(t -> !t.getCreationDate().isBefore(from) && !t.getCreationDate().isAfter(to))
                .toList();
    }

    public void addTransaction(String collectivityId, CollectivityTransaction transaction) {
        return;
    }
}
