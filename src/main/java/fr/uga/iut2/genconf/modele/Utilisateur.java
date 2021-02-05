package fr.uga.iut2.genconf.modele;

import java.io.Serializable;
import java.util.*;

import fr.uga.iut2.genconf.util.extension.CollectionExtension;
import fr.uga.iut2.genconf.vue.cli.CLI;
import org.apache.commons.validator.routines.EmailValidator;


public class Utilisateur implements Serializable {
    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation

    private final String email;
    private String nom;
    private String prenom;

    private final Map<String, Conference> conferencesAdministrees;  // association qualifiée par le nom

    private Map<String,Session> chairs;
    private Map<String, Communication> referents;
    private Map<String, Speaker> speakers;


    public Utilisateur(String email, String nom, String prenom) {
        assert EmailValidator.getInstance(false, false).isValid(email);
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.conferencesAdministrees = new HashMap<>();
        this.chairs = new HashMap<>();
        this.referents = new HashMap<>();
        this.speakers = new HashMap<>();
    }

    public String getEmail() {
        return this.email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void ajouteConferenceAdministree(Conference conf) {
        assert !this.conferencesAdministrees.containsKey(conf.getNom());

        this.conferencesAdministrees.put(conf.getNom(), conf);
    }

    public Map<String,Session> getChairs() {
        return this.chairs;
    }

    public Map<String, Communication> getReferents() {
        return referents;
    }

    public void removedReferent(String nom) {
        assert referents.get(nom) != null;

        referents.remove(nom);
    }

    public Map<String, Speaker> getSpeakers() {
        return speakers;
    }

    public void addReferents(Communication communication) {
        if(this.referents.containsKey(communication.getNom()))
            return;

        this.referents.put(communication.getNom(), communication);
    }

    public boolean addSpeakers(Speaker speaker) {
        if(this.speakers.containsKey(speaker.getCommunication().getNom()))
            return false;

        this.speakers.put(speaker.getCommunication().getNom(), speaker);
        return true;
    }

    public void addChairs(Session session) {
        assert !this.chairs.containsKey(session.getNom());

        this.chairs.put(session.getNom(), session);
    }

    public Map<String, Conference> getConferencesAdministrees() {
        return conferencesAdministrees;
    }

    @Override
    public String toString() {
        return "Nom Prenom: " + getNom() + " " + getPrenom() + "\n" +
                "Email : " + getEmail() + "\n" +
                "Est chair des sessions : " + CollectionExtension.KeysToString(getChairs()) + "\n" +
                "Est référent·e des communications : " + CollectionExtension.KeysToString(getReferents()) + "\n" +
                "Est speaker dans les communications : " + CollectionExtension.KeysToString(getSpeakers())+"\n" +
                "Administre les conférences : " + CollectionExtension.KeysToString(getConferencesAdministrees());
    }
}
