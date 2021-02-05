package fr.uga.iut2.genconf.controleur;

import fr.uga.iut2.genconf.modele.*;
import fr.uga.iut2.genconf.modele.enums.StatusConference;
import fr.uga.iut2.genconf.modele.enums.TypeCommunication;
import fr.uga.iut2.genconf.util.Reponse;
import fr.uga.iut2.genconf.vue.cli.CLI;
import fr.uga.iut2.genconf.vue.cli.rootage.Commande;
import fr.uga.iut2.genconf.vue.gui.GUI;
import fr.uga.iut2.genconf.vue.shared.IHM;
import fr.uga.iut2.genconf.vue.shared.dto.*;

import java.util.HashMap;
import java.util.Map;

public class Controleur {

    private final GenConf genconf;
    private final IHM ihm;

    public Controleur(GenConf genconf) {
        this.genconf = genconf;

        //this.ihm = new CLI(this);
        this.ihm = new GUI(this);
    }

    public void demarrer() {
        this.ihm.afficherInterface();
    }

    //-----  Éléments du dialogue  -------------------------------------------------
    public void gererDialogue(Commande cmd) {
        switch (cmd) {
            case QUITTER:
                this.ihm.fermerInterface();
                break;
            case CREER_UTILISATEUR: {
                this.ihm.saisirNouvelUtilisateur();
            }
            break;
            case CREER_CONFERENCE: {
                this.ihm.saisirNouvelleConference(this.genconf.getConferences().keySet());
            }
            break;
            case CREER_SESSION: {
                if(this.genconf.getConferences().isEmpty())
                {
                    this.ihm.informerUtilisateur("Il n'y a pas de conférences enregistrées", false);
                    return;
                }

                Conference conference = genconf.getConference(this.ihm.saisirConference(genconf.getConferences().keySet()));
                if (conference ==null) {
                    ihm.informerUtilisateur("La conférence n'existe pas",false);
                    return;
                }

                this.ihm.saisirNouvelleSession(conference.getSessions().keySet(), conference);
            }
            break;
            case CONSULTER_COMMUNICATION: {
                Reponse<Communication> reponse = this.getCommunication();
                if(!reponse.succeed())
                {
                    this.ihm.informerUtilisateur(reponse.getMessage(), reponse.succeed());
                    return;
                }
                Communication communication = reponse.getData();

                this.ihm.afficherCommunication(communication);
            }
            break;
            case CONSULTER_SESSION: {
                Reponse<Session> reponse = this.getSession();
                if(!reponse.succeed())
                {
                    this.ihm.informerUtilisateur(reponse.getMessage(), reponse.succeed());
                    return;
                }
                Session session = reponse.getData();

                this.ihm.afficherSession(session);
            }
            break;
            case CONSULTER_CONFERENCE: {
                if(this.genconf.getConferences().isEmpty())
                {
                    this.ihm.informerUtilisateur("Il n'y a pas de conférences enregistrées", false);
                    return;
                }

                String nomConference = this.ihm.saisirConference(genconf.getConferences().keySet());
                Conference conference = genconf.getConference(nomConference);
                if(conference == null) {
                    this.ihm.informerUtilisateur("La conference n'existe pas", false);
                    return;
                }

                this.ihm.afficherConference(conference);
            }
            break;
            case CREER_COMMUNICATION: {
                Reponse<Session> reponse = this.getSession();
                if(!reponse.succeed())
                {
                    this.ihm.informerUtilisateur(reponse.getMessage(), reponse.succeed());
                    return;
                }
                Session session = reponse.getData();

                this.ihm.saisirNouvelleCommunication(session.getCommunications().keySet(), session.getConference(),session);
            }
            break;
            case DONNER_DROIT_ADMIN_CONF: {
                if(this.genconf.getConferences().isEmpty())
                {
                    this.ihm.informerUtilisateur("Il n'y a pas de conférences enregistrées", false);
                    return;
                }

                String nomConference = this.ihm.saisirConference(genconf.getConferences().keySet());
                Conference conference = genconf.getConference(nomConference);
                if(conference == null) {
                    this.ihm.informerUtilisateur("La conference n'existe pas", false);
                    return;
                }
                this.ihm.donnerDroitAdminConf(conference);
            }
            break;
            case AJOUTER_SPEAKER: {
                Reponse<Communication> reponse = this.getCommunication();
                if(!reponse.succeed())
                {
                    this.ihm.informerUtilisateur(reponse.getMessage(), reponse.succeed());
                    return;
                }
                Communication communication = reponse.getData();

                this.ihm.ajouterSpeaker(communication.getSession().getConference(), communication.getSession(), communication);
            }
            break;
            case AJOUTER_CHAIRMAN_SESSION : {
                Reponse<Session> reponse = this.getSession();
                if(!reponse.succeed())
                {
                    this.ihm.informerUtilisateur(reponse.getMessage(), reponse.succeed());
                    return;
                }
                Session session = reponse.getData();

                this.ihm.ajouterChairman(session.getConference(), session);
            }
            break;
            case RENDRE_CONFERENCE_PUBLIC:
            {
                if (this.genconf.getConferences().isEmpty()) {
                    this.ihm.informerUtilisateur("Il n'y a pas de conférences enregistréee.",false);
                    return;
                }

                String nom = this.ihm.saisirConference(this.genconf.getConferences().keySet());

                if (!this.genconf.getConferences().containsKey(nom)) {
                    this.ihm.informerUtilisateur("La conférence n'existe pas.",false);
                    return;
                }
                this.rendreConferencePublic(nom, true);

            }
            break;
            case ASSOCIER_ARTICLE_COMMUNICATION:
            {
                Reponse<Communication> reponse = this.getCommunication();
                if(!reponse.succeed())
                {
                    this.ihm.informerUtilisateur(reponse.getMessage(), reponse.succeed());
                    return;
                }
                Communication communication = reponse.getData();

                if(!communication.getType().equals(TypeCommunication.Article))
                {
                    this.ihm.informerUtilisateur("La communication n'est pas de type article", false);
                    return;
                }

                this.ihm.associerArticleCommunication(communication.getSession().getConference(), communication.getSession(), communication);
            }
            break;
            case ASSOCIER_VIDEO_COMMUNICATION: {
                Reponse<Communication> reponse = this.getCommunication();
                if(!reponse.succeed())
                {
                    this.ihm.informerUtilisateur(reponse.getMessage(), reponse.succeed());
                    return;
                }
                Communication communication = reponse.getData();
                this.ihm.associerVideoCommunication(communication.getSession().getConference(), communication.getSession(), communication);
            }
            break;
            case ASSOCIER_SALLE_SESSION:
            {
                Reponse<Session> reponse = this.getSession();
                if(!reponse.succeed())
                {
                    this.ihm.informerUtilisateur(reponse.getMessage(), reponse.succeed());
                    return;
                }
                Session session = reponse.getData();

                this.ihm.associerSalleSession(session.getConference(), session);
            }
            break;
            case CHANGER_STATUT_CONFERENCE:
            {
                if(this.genconf.getConferences().isEmpty())
                {
                    this.ihm.informerUtilisateur("Il n'y a pas de conférences enregistrées", false);
                    return;
                }

                String nomConference = this.ihm.saisirConference(genconf.getConferences().keySet());
                Conference conference = genconf.getConference(nomConference);
                if(conference == null) {
                    this.ihm.informerUtilisateur("La conference n'existe pas", false);
                    return;
                }
                this.ihm.definirStatusConference(conference);
            }
            break;
            case CHANGER_REFERENT:
            {
                Reponse<Communication> reponse = this.getCommunication();
                if(!reponse.succeed())
                {
                    this.ihm.informerUtilisateur(reponse.getMessage(), reponse.succeed());
                    return;
                }
                Communication communication = reponse.getData();


                this.ihm.donnerDroitsCorrespondant(communication.getSession().getConference(), communication.getSession(), communication);
            }
            break;
            case CONSULTER_REFERENT:
            {
                Reponse<Communication> reponse = this.getCommunication();
                if(!reponse.succeed())
                {
                    this.ihm.informerUtilisateur(reponse.getMessage(), reponse.succeed());
                    return;
                }
                Communication communication = reponse.getData();

                this.ihm.afficherUtilisateur(communication.getReferent());
            }
            break;
            case CONSULTER_UTILISATEURS:
            {
                if (this.genconf.getUtilisateurs().isEmpty()) {
                    this.ihm.informerUtilisateur("Il n'existe aucun·e utilisateur·rice dans genconf.",false);
                    return;
                }
                ihm.afficherUtilisateurs(this.genconf.getUtilisateurs());
            }
            break;
            case CONSULTER_UTILISATEUR:
            {
                if (this.genconf.getUtilisateurs().isEmpty()) {
                    this.ihm.informerUtilisateur("Il n'existe aucun·e utilisateur·ice dans genconf.",false);
                    return;
                }
                String email = this.ihm.saisirUtilisateur(this.genconf.getUtilisateurs().keySet());
                Utilisateur utilisateur = this.genconf.getUtilisateur(email);
                if (utilisateur==null) {
                    ihm.informerUtilisateur("L'utilisateur·ice n'existe pas", false);
                    return;
                }
                ihm.afficherUtilisateur(utilisateur);
            }
            break;
            default:
                assert false: "Commande inconnue.";
        }
    }

    // ------Consulter--------------------------------------------------------
    public void consulterUtilisateurs() {
        this.ihm.afficherUtilisateurs(this.genconf.getUtilisateurs());
    }

    public void consulterUtilisateur(String emailUtilisateur) {
        Utilisateur utilisateur = this.genconf.getUtilisateur(emailUtilisateur);
        if(utilisateur == null)
        {
            this.ihm.informerUtilisateur("L'utilisateur·ice n'existe pas", false);
            return;
        }

        this.ihm.afficherUtilisateur(utilisateur);
    }

    public void consulterConferences(){

        Map<String, Conference> conferences = this.genconf.getConferences();
        this.ihm.afficherConferences(conferences);
    }

    public void consulterSessions(String nomConference){
        Conference conference = this.genconf.getConference(nomConference);
        if(conference == null)
        {
            this.ihm.informerUtilisateur("La conférence n'existe pas", false);
            return;
        }

        Map<String, Session> sessions = conference.getSessions();

        this.ihm.afficherSessions(sessions);
    }

    public void consulterCommunications(String nomConference, String nomSession){

        Reponse<Session> reponse = getSession(nomConference, nomSession);
        if(!reponse.succeed())
        {
            this.ihm.informerUtilisateur(reponse.getMessage(), reponse.succeed());
            return;
        }

        Session session = reponse.getData();
        this.ihm.afficherCommunications(session.getCommunications());
    }

    public void consulterConference(String nomConference) {

        Conference conference = this.genconf.getConference(nomConference);
        if(conference == null)
        {
            this.ihm.informerUtilisateur("La conférence n'existe pas", false);
            return;
        }
        this.ihm.afficherConference(conference);
    }

    public void consulterSession(String nomConference, String nomSession){

        Reponse<Session> reponse = getSession(nomConference, nomSession);
        if(!reponse.succeed())
        {
            this.ihm.informerUtilisateur(reponse.getMessage(), reponse.succeed());
            return;
        }
        this.ihm.afficherSession(reponse.getData());
    }

    public void consulterCommunication(String nomConference, String nomSession, String nomCommunication){
        Reponse<Communication> reponse = getCommunication(nomConference, nomSession, nomCommunication);

        if(!reponse.succeed())
        {
            this.ihm.informerUtilisateur(reponse.getMessage(), reponse.succeed());
            return;
        }
        this.ihm.afficherCommunication(reponse.getData());
    }

    public void consulterReferent(String nomConference, String nomSession, String nomCommunication){

        Reponse<Communication> reponse = getCommunication(nomConference, nomSession, nomCommunication);
        if(!reponse.succeed())
        {
            this.ihm.informerUtilisateur(reponse.getMessage(), reponse.succeed());
            return;
        }
        Communication communication = reponse.getData();

        Utilisateur utilisateur = communication.getReferent();
        if(utilisateur == null){
            this.ihm.informerUtilisateur("Le·a reférent·e n'existe pas", false);
            return;
        }

        this.ihm.afficherUtilisateur(utilisateur);
    }
    //-------------------------------------------------------------------

    //----- Creer--------------------------------------------------------
    public void creerUtilisateur(NouvelUtilisateur infos) {

        boolean success = this.genconf.ajouteUtilisateur(
                infos.email,
                infos.nom,
                infos.prenom
        );
        if (success) {
            this.ihm.informerUtilisateur(
                    "Nouvel·le utilisateur·ice : " + infos.prenom + " " + infos.nom + " <" + infos.email + ">",
                    true
            );
        } else {
            this.ihm.informerUtilisateur(
                    "L'utilisateur·ice " + infos.email + " est déjà connu·e de GenConf.",
                    false
            );
        }
    }

    public void creerSession(String nomConference, NouvelleSession model){

        Conference conference = this.genconf.getConference(nomConference);
        if(conference == null)
        {
            this.ihm.informerUtilisateur("La conference n'existe pas", false);
            return;
        }

        if(conference.getSession(model.nom) != null)
        {
            this.ihm.informerUtilisateur("Une session existe déjà avec ce nom", false);
            return;
        }

        conference.ajouteSession(model.nom, model.type, model.dateDebut, model.dateFin);

        this.ihm.informerUtilisateur("La session a été créée", true);
    }

    public void creerCommunication(String nomConference, String nomSession, NouvelleCommunication model){

        Reponse<Session> reponse = getSession(nomConference, nomSession);
        if(!reponse.succeed())
        {
            this.ihm.informerUtilisateur(reponse.getMessage(), reponse.succeed());
            return;
        }
        Session session = reponse.getData();

        if(session.getCommunication(model.nom) != null)
        {
            this.ihm.informerUtilisateur("Une communication existe déjà avec ce nom", false);
            return;
        }

        Utilisateur utilisateur = this.genconf.getUtilisateur(model.referent.email);
        if(utilisateur == null)
        {
            if(this.genconf.ajouteUtilisateur(model.referent.email, model.referent.nom, model.referent.prenom))
            {
                this.ihm.informerUtilisateur("L'utilisateur.ice n'existe pas", false);
                return;
            }

            utilisateur = this.genconf.getUtilisateur(model.referent.email);
        }

        session.ajouteCommunication(model.nom, model.type, model.dateDebut, model.dateFin, utilisateur);

        Communication communication = session.getCommunication(model.nom);
        communication.setReferent(utilisateur);

        this.ihm.informerUtilisateur("La communication a été ajoutée", true);
    }

    public void creerConference(NouvelleConference model) {

        if(this.genconf.getConferences().containsKey(model.nom))
        {
            this.ihm.informerUtilisateur("Une conference avec ce nom existe déja", false);
            return;
        }

        boolean nouvelUtilisateur = this.genconf.ajouteUtilisateur(
                model.admin.email,
                model.admin.nom,
                model.admin.prenom
        );

        if (!nouvelUtilisateur) {
            this.ihm.informerUtilisateur("L'utilisateur.ice n'a pas été crée", true);
        }

        this.genconf.nouvelleConference(
                model.nom,
                model.dateDebut,
                model.dateFin,
                model.admin.email,
                model.status
        );

        this.ihm.informerUtilisateur(
                "Nouvelle conférence : " + model.nom + ", administrée par " + model.admin.prenom + " " + model.admin.nom + " (" + model.admin.email + ") [" + model.status + "]",
                true
        );
    }
    //-----------------------------------------------------------------------

    // ------Associer--------------------------------------------------------
    public void associerSalleSession(String nomConference, String nomSession, String nomSalle){
        Reponse<Session> reponse = getSession(nomConference, nomSession);
        if(!reponse.succeed())
        {
            this.ihm.informerUtilisateur(reponse.getMessage(), reponse.succeed());
            return;
        }
        Session session = reponse.getData();

        session.setSalle(nomSalle);

        this.ihm.informerUtilisateur("La salle a été associée à la session", true);
    }

    public void associerArticleCommunication(String nomConference, String nomSession, String nomCommunication, NouvelArticle article){
        Reponse<Communication> reponse = getCommunication(nomConference, nomSession, nomCommunication);
        if(!reponse.succeed())
        {
            this.ihm.informerUtilisateur("Erreurs lors de la requête modèle : " + reponse.getMessage(), reponse.succeed());
            return;
        }
        Communication communication = reponse.getData();

        if(!communication.getType().equals(TypeCommunication.Article))
        {
            this.ihm.informerUtilisateur("La communication n'est pas de type article", false);
            return;
        }

        HashMap<String, Auteur> auteurs = new HashMap<>();
                article.auteurs.values().stream()
                .map(m -> new Auteur(m.nom, m.prenom, m.email))
                .forEach(entry-> auteurs.put(entry.getEmail(), entry));

        communication.setArticle(new Article(article.nom, article.lienPdf, auteurs));
        for (Auteur auteur : auteurs.values()) {
            if (this.genconf.getUtilisateurs().containsKey(auteur.getEmail())) {
                communication.addSpeaker(this.genconf.getUtilisateur(auteur.getEmail()));
            } else {
                communication.addSpeaker(auteur.getNom(), auteur.getPrenom(), auteur.getEmail());
            }
        }

        this.ihm.informerUtilisateur("L'article a ete associé a la communication", true);
    }
    //--------------------------------------------------------------------

    // -----Ajouter--------------------------------------------------------
    public void ajouterSpeaker(String nomConference, String nomSession, String nomCommunication, NouveauSpeaker model){

        Reponse<Communication> reponse = getCommunication(nomConference, nomSession, nomCommunication);
        if(!reponse.succeed()) {
            this.ihm.informerUtilisateur(reponse.getMessage(), reponse.succeed());
            return;
        }
        Communication communication = reponse.getData();

        if(communication.isSpeaker(model.email)) {
            this.ihm.informerUtilisateur("L'utilisateur.ice est déjà speaker", false);
            return;
        }

        Utilisateur utilisateur = this.genconf.getUtilisateur(model.email);
        if(utilisateur == null) {
            communication.addSpeaker(model.nom, model.prenom, model.email);
        }
        else {
            communication.addSpeaker(utilisateur);
        }
        this.ihm.informerUtilisateur("Le speaker a été ajouté", true);
    }

    public void ajouterChairManSession(String nomConference, String nomSession, String email) {

        Reponse<Session> reponse = getSession(nomConference, nomSession);
        if(!reponse.succeed())
        {
            this.ihm.informerUtilisateur(reponse.getMessage(), reponse.succeed());
            return;
        }
        Session session = reponse.getData();

        Utilisateur utilisateur = this.genconf.getUtilisateur(email);
        if (utilisateur == null) {
            this.ihm.informerUtilisateur("L'utisateur.ice n'existe pas , le créer avant de l'ajouter comme chairman", false);
            return;
        }

        if (session.isChairMan(email)) {
            this.ihm.informerUtilisateur("L'utilisateur.ice est déjà chairman", false);
            return;
        }

        session.ajouteChairman(utilisateur);

        this.ihm.informerUtilisateur("Le chairman a été ajouté", true);
    }

    public void associerVideoCommunication(String nomConference, String nomSession, String nomCommunication, String lienVideo) {
        Reponse<Communication> reponse = getCommunication(nomConference, nomSession, nomCommunication);

        if(!reponse.succeed())
        {
            this.ihm.informerUtilisateur(reponse.getMessage(), false);
            return;
        }

        Communication communication = reponse.getData();
        communication.setLienVideo(lienVideo);

        this.ihm.informerUtilisateur("Video associée a la communication", true);
    }
    //--------------------------------------------------------

    //droit
    public void donnerDroitAdminConf(String nom, String email) {

        Conference conference = this.genconf.getConference(nom);
        if(conference == null)
        {
            this.ihm.informerUtilisateur("La conférence n'existe pas.", false);
            return;
        }

        Utilisateur utilisateur = this.genconf.getUtilisateur(email);
        if(utilisateur == null)
        {
            this.ihm.informerUtilisateur("L'utilisateur.ice n'existe pas", false);
            return;
        }

        if(conference.isAdministrateur(email))
        {
            this.ihm.informerUtilisateur("L'utilisateur.ice est déjà administrateur de la conférence", false);
            return;
        }

        conference.ajouteAdministrateur(utilisateur);

        this.ihm.informerUtilisateur("L'utilisateur.ice est maintenant administrateur de la conférence", true);
    }

    public void donnerDroitsCorrespondant(String nomConference, String nomSession, String nomCommunication, String email) {
        Reponse<Communication> reponse = getCommunication(nomConference, nomSession, nomCommunication);
        if(!reponse.succeed())
        {
            this.ihm.informerUtilisateur(reponse.getMessage(), false);
            return;
        }

        Communication communication = reponse.getData();

        Utilisateur utilisateur = this.genconf.getUtilisateur(email);
        if (utilisateur == null)
        {
            this.ihm.informerUtilisateur("L'utilisateur.ice n'existe pas", false);
            return;
        }

        communication.setReferent(utilisateur);
        this.ihm.informerUtilisateur("L'utilisateur.ice est maintenant correspondant", true);
    }

    public void rendreConferencePublic(String nomConference, boolean isPublic){
        Conference conference = this.genconf.getConference(nomConference);
        if(conference == null){
            this.ihm.informerUtilisateur("La conference n'existe pas", false);
            return;
        }

        conference.setPublic(isPublic);
        this.ihm.informerUtilisateur(
                "Le site de la conférence est maintenant " + (isPublic ? "public" : "privé"),
                true);
    }

    public void changerStatusConference(String nomConference, StatusConference status){
        // TODO : a faire
        Conference conference = this.genconf.getConference(nomConference);
        if (conference == null) {
            this.ihm.informerUtilisateur("La conférence n'existe pas", false);
            return;
        }

        conference.setStatus(status);

        this.ihm.informerUtilisateur("Le statut de la conférence a changé", true);
    }
    //--------------------------------------------------------------------------


    // Private API
    private Reponse<Session> getSession(String nomConference, String nomSession){

        Conference conference = this.genconf.getConference(nomConference);
        if(conference == null){
            return new Reponse<>("La conférence n'existe pas");
        }

        Session session = conference.getSession(nomSession);
        if(session == null){
            return new Reponse<>("La session n'existe pas");
        }

        return new Reponse<>(session);
    }

    private Reponse<Communication> getCommunication(String nomConference, String nomSession, String nomCommunication){
        Reponse<Session> reponse = getSession(nomConference, nomSession);

        if(!reponse.succeed()) {
            return new Reponse<>(reponse.getMessage());
        }

        Communication communication = reponse.getData().getCommunication(nomCommunication);

        if(communication == null){
            return new Reponse<>("La communication n'existe pas");
        }

        return new Reponse<>(communication);
    }
    // --------------------------------------------------------------------------

    // Private CLI API
    private Reponse<Session> getSession(){

        if(this.genconf.getConferences().isEmpty())
            return new Reponse<>("Il n'y a pas de conférence");

        String nomConference = this.ihm.saisirConference(genconf.getConferences().keySet());
        Conference conference = genconf.getConference(nomConference);
        if(conference == null)
            return new Reponse<>("La conference n'existe pas");

        if(conference.getSessions().isEmpty())
            return new Reponse<>("Il n'y a pas de sessions");

        String nomSession = this.ihm.saisirSession(conference.getSessions().keySet());
        Session session = conference.getSession(nomSession);

        return session == null ? new Reponse<>("La session n'existe pas") : new Reponse<>(session);
    }

    private Reponse<Communication> getCommunication(){

        if(this.genconf.getConferences().isEmpty())
            return new Reponse<>("Il n'y a pas de conférences enregistrées.");

        String nomConference = this.ihm.saisirConference(genconf.getConferences().keySet());
        Conference conference = genconf.getConference(nomConference);
        if(conference == null)
            return new Reponse<>("La conférence n'existe pas");

        if(conference.getSessions().isEmpty())
            return new Reponse<>("Il n'y a pas de sessions enregistrées dans cette conférence.");

        String nomSession = this.ihm.saisirSession(conference.getSessions().keySet());
        Session session = conference.getSession(nomSession);
        if(session == null)
            return new Reponse<>("La session n'existe pas.");

        if(session.getCommunications().isEmpty())
            return new Reponse<>("Il n'y a pas de communications dans cette session.");

        String nomCommunication = this.ihm.saisirCommunication(session.getCommunications().keySet());
        Communication communication = session.getCommunication(nomCommunication);

        return communication == null ? new Reponse<>("La communication n'existe pas.") : new Reponse<>(communication);
    }
    // --------------------------------------------------------------------------
}
