package com.collectivite.Agricole.model;

import java.time.LocalDate;

public class Payment {
    private Long id;
    private String memberId;
    private String collectivityId;
    private double amount;
    private LocalDate paymentDate;
    private String paymentMode; // ESPECE, VIREMENT_BANCAIRE, MOBILE_MONEY
    private String reference;

    public Payment() {}

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public String getCollectivityId() { return collectivityId; }
    public void setCollectivityId(String collectivityId) { this.collectivityId = collectivityId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }
    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
}