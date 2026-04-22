package com.collectivite.Agricole.service;

import com.collectivite.Agricole.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public Payment recordPayment(String id, Payment payment) {
        return payment;
    }
}
