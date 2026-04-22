package com.collectivite.Agricole.repository;

import com.collectivite.Agricole.model.MemberPayment;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberPaymentRepository {
    private final Map<String, MemberPayment> storage = new ConcurrentHashMap<>();

    public MemberPayment save(MemberPayment payment) {
        if (payment.getId() == null) {
            payment.setId(UUID.randomUUID().toString());
        }
        storage.put(payment.getId(), payment);
        return payment;
    }

    public List<MemberPayment> findByMemberId(String memberId) {
        return storage.values().stream()
                .filter(p -> p.getMemberId().equals(memberId))
                .toList();
    }

    public List<MemberPayment> findByCollectivityId(String collectivityId) {
        // On a besoin de faire une jointure avec membership_fees. Pour simplifier, on peut stocker collectivityId dans payment.
        // Ici on suppose qu'on a un champ collectivityId dans MemberPayment. Ajoute-le si besoin.
        return storage.values().stream()
                .filter(p -> p.getMembershipFeeId() != null) // à améliorer
                .toList();
    }
}