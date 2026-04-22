package com.collectivite.Agricole.model;

public class MobileBankingAccount implements FinancialAccount {
    private String id;
    private String holderName;
    private String mobileBankingService; // AIRTEL_MONEY, MVOLA, ORANGE_MONEY
    private int mobileNumber;
    private double amount;

    // getters et setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getHolderName() { return holderName; }
    public void setHolderName(String holderName) { this.holderName = holderName; }
    public String getMobileBankingService() { return mobileBankingService; }
    public void setMobileBankingService(String mobileBankingService) { this.mobileBankingService = mobileBankingService; }
    public int getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(int mobileNumber) { this.mobileNumber = mobileNumber; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}