package com.collectivite.Agricole.model;


import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Member {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String address;
    private String job;
    private String phone;
    private String email;
    private LocalDate membershipDate;
    private Collectivity collectivity;
    private List<Sponsor> sponsors = new ArrayList<>();
    private boolean feesPaid;
    private boolean annualContributionsPaid;
    private String collectivityId;

    // Getters et setters (tous)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getMembershipDate() { return membershipDate; }
    public void setMembershipDate(LocalDate membershipDate) { this.membershipDate = membershipDate; }
    public Collectivity getCollectivity() { return collectivity; }
    public void setCollectivity(Collectivity collectivity) { this.collectivity = collectivity; }
    public List<Sponsor> getSponsors() { return sponsors; }
    public void setSponsors(List<Sponsor> sponsors) { this.sponsors = sponsors; }
    public boolean isFeesPaid() { return feesPaid; }
    public void setFeesPaid(boolean feesPaid) { this.feesPaid = feesPaid; }
    public boolean isAnnualContributionsPaid() { return annualContributionsPaid; }
    public void setAnnualContributionsPaid(boolean annualContributionsPaid) { this.annualContributionsPaid = annualContributionsPaid; }

    public String getCollectivityId() {
        String CollectivityId = "";
        return CollectivityId ;
    }
}