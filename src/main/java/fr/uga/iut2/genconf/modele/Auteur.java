package fr.uga.iut2.genconf.modele;

import java.io.Serializable;

public class Auteur implements Serializable {
    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation

    private final String nom;
    private final String prenom;
    private final String email;

    public Auteur(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public String getEmail() {return email;}
}
