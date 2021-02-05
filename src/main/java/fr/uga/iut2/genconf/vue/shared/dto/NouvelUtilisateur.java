package fr.uga.iut2.genconf.vue.shared.dto;

public class NouvelUtilisateur {

    public final String email;
    public final String nom;
    public final String prenom;

    public NouvelUtilisateur(String email, String nom, String prenom){
        this.email = email.isEmpty() ? "default@gmail.com" : email;
        this.nom = nom.isEmpty() ? "default" : nom;
        this.prenom = prenom.isEmpty() ? "default" : prenom;
    }
}
