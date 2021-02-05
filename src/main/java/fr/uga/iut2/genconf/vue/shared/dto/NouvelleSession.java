package fr.uga.iut2.genconf.vue.shared.dto;

import fr.uga.iut2.genconf.modele.Session;
import fr.uga.iut2.genconf.modele.enums.TypeSession;

import java.time.LocalDate;

public class NouvelleSession {

    public final String nom;
    public final TypeSession type;
    public final LocalDate dateDebut;
    public final LocalDate dateFin;

    public NouvelleSession(
            String nom,
            TypeSession type,
            LocalDate dateDebut,
            LocalDate dateFin
    ){
        this.nom = nom.isEmpty() ? "default" : nom;
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
}
