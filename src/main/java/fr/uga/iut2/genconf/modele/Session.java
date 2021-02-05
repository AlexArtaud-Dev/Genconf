package fr.uga.iut2.genconf.modele;

import fr.uga.iut2.genconf.modele.enums.TypeCommunication;
import fr.uga.iut2.genconf.modele.enums.TypeSession;
import fr.uga.iut2.genconf.util.extension.CollectionExtension;
import fr.uga.iut2.genconf.util.extension.DateExtension;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Session implements Serializable {
    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation

    private final String nom;
    private final LocalDate dateDebut;
    private final LocalDate dateFin;
    private String salle;

    private final TypeSession type;


    private final Conference conference;
    private Map<String, Communication> communications;
    private Map<String, Utilisateur> chairmans;


    public Session(Conference conference, String nom, TypeSession type, LocalDate dateDebut, LocalDate dateFin) {
        assert !dateFin.isAfter(dateDebut);

        this.conference = conference;

        this.nom = nom;
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.salle = "Non attitré";

        this.communications = new HashMap<>();
        this.chairmans = new HashMap<>();
    }

    public void setSalle(String salle){
        this.salle = salle;
    }

    public void ajouteCommunication(String nom, TypeCommunication type, LocalDate dateDebut, LocalDate dateFin, Utilisateur referent){
        assert !communications.containsKey(nom);

        Communication communication = new Communication(this, nom, type, dateDebut, dateFin, referent);
        communications.put(nom, communication);
    }

    public void ajouteChairman(Utilisateur utilisateur){
        assert !chairmans.containsKey(utilisateur.getEmail());

        chairmans.put(utilisateur.getEmail(), utilisateur);
        utilisateur.addChairs(this);
    }

    public Map<String, Utilisateur> getChairmans(){
        return chairmans;
    }

    public Conference getConference(){
        return conference;
    }

    public Map<String, Communication> getCommunications(){
        return communications;
    }

    public Communication getCommunication(String nom){
        return communications.get(nom);
    }

    public String getNom() {
        return this.nom;
    }


    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public String getSalle() {
        return salle;
    }

    public TypeSession getType() {
        return type;
    }

    public Boolean isChairMan(String email) { return chairmans.containsKey(email); }

    @Override
    public String toString() {
        return  "Nom : " + getNom() + "\n" +
                "Commence le " + DateExtension.getAs_ISO_8061(getDateDebut()) + " et finit le " + DateExtension.getAs_ISO_8061(getDateFin()) + "\n" +
                "Salle : " + getSalle() + "\n" +
                "Type : " + getType() + "\n" +
                "Chairmans : " + getChairmans().values().stream().map(c -> c.getPrenom() + " "+ c.getNom()).collect(Collectors.joining(", ")) + "\n" +
                "Communications de la session : " + CollectionExtension.KeysToString(getCommunications());
    }
}
