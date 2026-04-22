package com.collectivite.Agricole.model;

import java.time.LocalDate;

public class Contribution {
    private Long id;
    private String collectivityId;
    private double amount;
    private String periodicity; // MENSUELLE, ANNUELLE, PONCTUELLE
    private LocalDate startDate;
    private LocalDate endDate;

    // Constructeurs
    public Contribution() {}

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCollectivityId() { return collectivityId; }
    public void setCollectivityId(String collectivityId) { this.collectivityId = collectivityId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getPeriodicity() { return periodicity; }
    public void setPeriodicity(String periodicity) { this.periodicity = periodicity; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}