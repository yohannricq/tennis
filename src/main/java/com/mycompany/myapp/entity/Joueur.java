package com.mycompany.myapp.entity;

public class Joueur {

    private Long id;
    private String nom;
    private String prenom;
    private Character sexe;

    public Joueur() {
    }


    public Joueur(Long id, String nom, String prenom, Character sexe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
    }


    public Joueur(String nom, String prenom, Character sexe) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Character getSexe() {
        return this.sexe;
    }

    public void setSexe(Character sexe) {
        this.sexe = sexe;
    }

}
