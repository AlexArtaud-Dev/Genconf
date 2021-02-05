package fr.uga.iut2.genconf.vue.shared.dto;

import fr.uga.iut2.genconf.modele.Conference;
import fr.uga.iut2.genconf.modele.enums.StatusConference;

import java.time.LocalDate;

public class NouvelleConference {

    public final String nom;
    public final LocalDate dateDebut;
    public final LocalDate dateFin;
    public final StatusConference status;
    public final NouvelUtilisateur admin;

    public NouvelleConference(final String nom,
                                   final LocalDate dateDebut,
                                   final LocalDate dateFin,
                                   final NouvelUtilisateur admin,
                                   StatusConference status) {
        this.nom = nom.isEmpty() ? "default" : nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.admin = admin;
        this.status = status;
    }
}
