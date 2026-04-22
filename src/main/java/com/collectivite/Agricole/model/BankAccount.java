package com.collectivite.Agricole.model;


public class BankAccount implements FinancialAccount {
    private String id;
    private String holderName;
    private String bankName;
    private int bankCode;
    private int bankBranchCode;
    private int bankAccountNumber;
    private int bankAccountKey;
    private double amount;

    // getters et setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getHolderName() { return holderName; }
    public void setHolderName(String holderName) { this.holderName = holderName; }
    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }
    public int getBankCode() { return bankCode; }
    public void setBankCode(int bankCode) { this.bankCode = bankCode; }
    public int getBankBranchCode() { return bankBranchCode; }
    public void setBankBranchCode(int bankBranchCode) { this.bankBranchCode = bankBranchCode; }
    public int getBankAccountNumber() { return bankAccountNumber; }
    public void setBankAccountNumber(int bankAccountNumber) { this.bankAccountNumber = bankAccountNumber; }
    public int getBankAccountKey() { return bankAccountKey; }
    public void setBankAccountKey(int bankAccountKey) { this.bankAccountKey = bankAccountKey; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
