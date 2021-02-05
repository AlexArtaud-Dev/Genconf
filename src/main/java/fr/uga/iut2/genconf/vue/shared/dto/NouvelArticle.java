package fr.uga.iut2.genconf.vue.shared.dto;

import java.util.HashMap;
import java.util.Set;

public class NouvelArticle {

    public final String nom;
    public final String lienPdf;
    public final HashMap<String, NouvelAuteur> auteurs;

    public NouvelArticle(final String nom, final String lienPdf, HashMap<String, NouvelAuteur> auteurs){
        this.nom = nom.isEmpty() ? "default" : nom;
        this.lienPdf = lienPdf;
        this.auteurs = auteurs;
    }
}
