package com.collectivite.Agricole.model;

// CashAccount
public class CashAccount implements FinancialAccount {
    private String id;
    private double amount;

    public CashAccount() {}
    public CashAccount(String id, double amount) { this.id = id; this.amount = amount; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
