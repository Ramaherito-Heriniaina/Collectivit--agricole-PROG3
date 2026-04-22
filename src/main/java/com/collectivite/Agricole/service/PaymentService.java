package com.collectivite.Agricole.service;

import com.collectivite.Agricole.exception.BusinessException;
import com.collectivite.Agricole.model.Payment;
import com.collectivite.Agricole.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {
        if (payment.getAmount() <= 0) {
            throw new BusinessException("Le montant du paiement doit être supérieur à 0");
        }
        if (payment.getPaymentDate() == null) {
            payment.setPaymentDate(LocalDate.now());
        }
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Paiement non trouvé"));
    }

    public Payment recordPayment(String id, Payment payment) {
        return payment;
            }

    public java.awt.List createPayments(String memberId, java.awt.List payments) {
        return payments;
    }
}
