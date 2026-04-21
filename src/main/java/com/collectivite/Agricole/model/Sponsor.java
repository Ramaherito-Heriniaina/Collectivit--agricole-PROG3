package com.collectivite.Agricole.model;


public class Sponsor {
    private Long sponsorMemberId;
    private String relation;

    // Getters et setters
    public Long getSponsorMemberId() { return sponsorMemberId; }
    public void setSponsorMemberId(Long sponsorMemberId) { this.sponsorMemberId = sponsorMemberId; }
    public String getRelation() { return relation; }
    public void setRelation(String relation) { this.relation = relation; }
}