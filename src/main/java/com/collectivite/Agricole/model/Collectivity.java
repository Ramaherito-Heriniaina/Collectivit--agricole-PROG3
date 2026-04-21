package com.collectivite.Agricole.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Collectivity {
    private Long id;
    private String name;
    private String city;
    private String specialty;
    private LocalDate creationDate;
    private boolean authorized;
    private List<Member> foundingMembers = new ArrayList<>();

    // Constructeurs
    public Collectivity() {}

    public Collectivity(String name, String city, String specialty) {
        this.name = name;
        this.city = city;
        this.specialty = specialty;
    }

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public LocalDate getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }
    public boolean isAuthorized() { return authorized; }
    public void setAuthorized(boolean authorized) { this.authorized = authorized; }
    public List<Member> getFoundingMembers() { return foundingMembers; }
    public void setFoundingMembers(List<Member> foundingMembers) { this.foundingMembers = foundingMembers; }
}