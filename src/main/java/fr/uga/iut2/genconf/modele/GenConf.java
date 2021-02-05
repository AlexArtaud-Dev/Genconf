package fr.uga.iut2.genconf.modele;

import fr.uga.iut2.genconf.modele.enums.StatusConference;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class GenConf implements Serializable {
    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation

    private final Map<String, Utilisateur> utilisateurs;  // association qualifiée par l'email
    private final Map<String, Conference> conferences;  // association qualifiée par le nom

    public GenConf() {
        this.utilisateurs = new HashMap<>();
        this.conferences = new HashMap<>();
    }

    public boolean ajouteUtilisateur(String email, String nom, String prenom) {
        if (this.utilisateurs.containsKey(email)) {
            return false;
        } else {
            this.utilisateurs.put(email, new Utilisateur(email, nom, prenom));
            return true;
        }
    }

    public Map<String, Conference> getConferences() {
        return this.conferences;
    }

    public void nouvelleConference(String nom, LocalDate dateDebut, LocalDate dateFin, String adminEmail, StatusConference status) {
        assert !this.conferences.containsKey(nom);
        assert this.utilisateurs.containsKey(adminEmail);

        Utilisateur admin = this.utilisateurs.get(adminEmail);
        Conference conf = Conference.initialiseConference(this, nom, dateDebut, dateFin, status, admin);
        this.conferences.put(nom, conf);
    }

    public Conference getConference(String nom) {
        return this.conferences.get(nom);
    }

    public Map<String, Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public Utilisateur getUtilisateur(String email) {
        return this.utilisateurs.get(email);


    }
}
