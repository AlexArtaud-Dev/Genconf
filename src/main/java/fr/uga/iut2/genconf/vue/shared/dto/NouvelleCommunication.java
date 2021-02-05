package fr.uga.iut2.genconf.vue.shared.dto;

import fr.uga.iut2.genconf.modele.enums.TypeCommunication;

import java.time.LocalDate;

public class NouvelleCommunication {

    public final String nom;
    public final TypeCommunication type;
    public final LocalDate dateDebut;
    public final LocalDate dateFin;

    public final NouvelUtilisateur referent;

    public NouvelleCommunication(
            String nom,
            TypeCommunication type,
            LocalDate dateDebut,
            LocalDate dateFin,
            NouvelUtilisateur referent
    ){
        this.nom = nom.isEmpty() ? "default" : nom;
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.referent = referent;
    }
}
