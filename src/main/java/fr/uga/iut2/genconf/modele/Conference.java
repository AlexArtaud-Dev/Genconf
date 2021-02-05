package fr.uga.iut2.genconf.modele;

import fr.uga.iut2.genconf.modele.enums.StatusConference;
import fr.uga.iut2.genconf.modele.enums.TypeSession;
import fr.uga.iut2.genconf.util.extension.DateExtension;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class Conference implements Serializable {
    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation

    private final GenConf genconf;

    private final String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private StatusConference status;
    private boolean isPublic;

    private final Map<String, Utilisateur> administrateurs;  // association qualifiée par l'email
    private Map<String, Session> sessions;


    // Invariant de classe : !dateDebut.isAfter(dateFin)
    //     On utilise la négation ici pour exprimer (dateDebut <= dateFin), ce
    //     qui est équivalent à !(dateDebut > dateFin).

    public Conference(GenConf genconf, String nom, LocalDate dateDebut, LocalDate dateFin, StatusConference status) {
        assert !dateDebut.isAfter(dateFin);

        this.genconf = genconf;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;

        this.administrateurs = new HashMap<>();
        this.sessions = new HashMap<>();
        this.status = status;
    }

    public static Conference initialiseConference(GenConf genconf, String nom, LocalDate dateDebut, LocalDate dateFin, StatusConference status, Utilisateur admin) {
        Conference conf = new Conference(genconf, nom, dateDebut, dateFin, status);
        conf.ajouteAdministrateur(admin);
        return conf;
    }

    public void setDateDebut(LocalDate dateDebut) {
        assert !dateDebut.isAfter(this.dateFin);

        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        assert !this.dateDebut.isAfter(dateFin);

        this.dateFin = dateFin;
    }

    public void ajouteAdministrateur(Utilisateur admin) {
        assert !this.administrateurs.containsKey(admin.getEmail());

        this.administrateurs.put(admin.getEmail(), admin);
        admin.ajouteConferenceAdministree(this);
    }

    public void ajouteSession(String nom, TypeSession type, LocalDate dateDebut, LocalDate dateFin) {
        assert !this.sessions.containsKey(nom);

        this.sessions.put(nom, new Session(this, nom, type, dateDebut, dateFin));
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public void setStatus(StatusConference status) {
        this.status = status;
    }

    public Boolean isPublic() { return this.isPublic; }

    public boolean isAdministrateur(String email)
    {
        return this.administrateurs.containsKey(email);
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public Map<String, Utilisateur> getAdministrateurs(){
        return this.administrateurs;
    }

    public String getNom() {
        return this.nom;
    }

    public StatusConference getStatus(){ return this.status; }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public Map<String, Session> getSessions() {
        return this.sessions;
    }

    public Session getSession(String nom) {
        return this.sessions.get(nom);
    }


    @Override
    public String toString() {
        return  "Nom : " + getNom() + " -> [" + (isPublic() ? "publique" : "privée") + "]" + "\n" +
                "Commence le " + DateExtension.getAs_ISO_8061(getDateDebut()) +  " et finit le " + DateExtension.getAs_ISO_8061(getDateFin()) + ", se déroule en " + getStatus() + "\n" +
                "Administrateur·ice·s : " + getAdministrateurs().values().stream().map(a -> a.getPrenom() + " " + a.getNom()).collect(Collectors.joining(", "))+
                "\nSessions :" + sessions.values().stream().map(sess -> sess.getNom() + " , "+ sess.getType()).collect(Collectors.joining(" ; "));
    }
}
