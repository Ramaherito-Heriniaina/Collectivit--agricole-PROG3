package com.collectivite.Agricole.model;

import java.time.LocalDate;

public class MemberPayment {
    private String id;
    private String memberId;
    private String membershipFeeId;
    private String accountCreditedId;
    private double amount;
    private String paymentMode; // CASH, MOBILE_BANKING, BANK_TRANSFER
    private LocalDate creationDate;

    public MemberPayment() {}

    // Getters et setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public String getMembershipFeeId() { return membershipFeeId; }
    public void setMembershipFeeId(String membershipFeeId) { this.membershipFeeId = membershipFeeId; }
    public String getAccountCreditedId() { return accountCreditedId; }
    public void setAccountCreditedId(String accountCreditedId) { this.accountCreditedId = accountCreditedId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
    public LocalDate getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }
}