package fr.uga.iut2.genconf.modele;

import java.io.Serializable;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Article implements Serializable {
    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation

    private final String nom;
    private final String lienPdf;

    private HashMap<String, Auteur> auteurs;

    public Article(String nom, String lienPdf, HashMap<String, Auteur> auteurs) {
        this.nom = nom;
        this.lienPdf = lienPdf;

        this.auteurs = auteurs;
    }

    public String getNom() {
        return nom;
    }

    public String getLienPdf() {
        return lienPdf;
    }

    public HashMap<String, Auteur> getAuteurs() {
        return auteurs;
    }

    @Override
    public String toString() {
        return "Article " + this.getNom() + " : par "+
                auteurs.values().stream().map(aut -> aut.getPrenom() + " "+
                aut.getNom()).collect(Collectors.joining(", ")) +
                "\nAdresse de l'article : " + this.lienPdf;
    }
}
