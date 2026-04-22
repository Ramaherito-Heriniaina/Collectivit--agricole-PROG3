package com.collectivite.Agricole.model;

import java.time.LocalDate;

public class Account {
    private Long id;
    private String entityType;   // "COLLECTIVITY" ou "FEDERATION"
    private String entityId;     // id de la collectivité (ou null pour fédération)
    private String accountType;  // "CAISSE", "BANCAIRE", "MOBILE_MONEY"
    private String holderName;
    private double currentBalance;
    private LocalDate lastUpdate;

    // Champs spécifiques aux comptes bancaires
    private String bankName;
    private String bankCode;
    private String branchCode;
    private String accountNumber;
    private String ribKey;

    // Champs spécifiques aux comptes mobile money
    private String mobileOperator;
    private String phoneNumber;

    public Account() {}

    // Getters et setters (tous les champs)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEntityType() { return entityType; }
    public void setEntityType(String entityType) { this.entityType = entityType; }
    public String getEntityId() { return entityId; }
    public void setEntityId(String entityId) { this.entityId = entityId; }
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    public String getHolderName() { return holderName; }
    public void setHolderName(String holderName) { this.holderName = holderName; }
    public double getCurrentBalance() { return currentBalance; }
    public void setCurrentBalance(double currentBalance) { this.currentBalance = currentBalance; }
    public LocalDate getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(LocalDate lastUpdate) { this.lastUpdate = lastUpdate; }
    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }
    public String getBankCode() { return bankCode; }
    public void setBankCode(String bankCode) { this.bankCode = bankCode; }
    public String getBranchCode() { return branchCode; }
    public void setBranchCode(String branchCode) { this.branchCode = branchCode; }
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public String getRibKey() { return ribKey; }
    public void setRibKey(String ribKey) { this.ribKey = ribKey; }
    public String getMobileOperator() { return mobileOperator; }
    public void setMobileOperator(String mobileOperator) { this.mobileOperator = mobileOperator; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}