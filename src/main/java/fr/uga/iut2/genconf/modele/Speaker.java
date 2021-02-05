package fr.uga.iut2.genconf.modele;

import java.io.Serializable;

public class Speaker implements Serializable {
    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation

    private final String nom;
    private final String prenom;
    private final String email;

    private Utilisateur utilisateur;
    private final Communication communication;

    public Speaker(Communication communication, String nom, String prenom, String email) {

        this.communication = communication;

        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public Speaker(Communication communication, Utilisateur utilisateur)
    {
        this(communication, utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail());
        this.utilisateur = utilisateur;
    }

    public Communication getCommunication(){
        return communication;
    }

    public String getNom() {
        return this.utilisateur == null ? nom : utilisateur.getNom();
    }

    public String getPrenom() {
        return this.utilisateur == null ? prenom : utilisateur.getPrenom();
    }

    public String getEmail() {
        return this.utilisateur == null ? email : utilisateur.getEmail();
    }

}
