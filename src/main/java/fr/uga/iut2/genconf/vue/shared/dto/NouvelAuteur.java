package fr.uga.iut2.genconf.vue.shared.dto;

public class NouvelAuteur {

    public final String nom;
    public final String prenom;
    public final String email;

    public NouvelAuteur(final String nom, final String prenom, String email){
        this.nom = nom.isEmpty() ? "default" : nom;
        this.prenom = prenom.isEmpty() ? "default" : prenom;
        this.email = email.isEmpty() ? "default" : email;
    }
}
