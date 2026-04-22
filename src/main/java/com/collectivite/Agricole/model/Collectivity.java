package com.collectivite.Agricole.model;

import java.time.LocalDate;
import java.util.List;

public class Collectivity {
    private String id;          // identifiant technique (UUID)
    private String location;
    private List<Member> members;
    private String numero;      // numéro unique attribué par la fédération
    private String nom;         // nom unique attribué par la fédération
    private LocalDate creationDate;
    private boolean authorized;
    

    public void setAuthorized(boolean b) {
        return;
    }

    public String getId() {
        return id;
    }

    public Object getNom() {
        return nom;
    }

    public Object getNumero() {
        return numero;
    }

    public void setNom(String nom) {
    }

    public void setNumero(String numero) {
    }

    public void setCreationDate(LocalDate now) {
    }
}