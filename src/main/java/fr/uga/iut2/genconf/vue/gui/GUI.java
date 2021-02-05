package fr.uga.iut2.genconf.vue.gui;

import fr.uga.iut2.genconf.vue.cli.rootage.Commande;
import fr.uga.iut2.genconf.controleur.Controleur;
import fr.uga.iut2.genconf.modele.Communication;
import fr.uga.iut2.genconf.modele.Conference;
import fr.uga.iut2.genconf.modele.Session;
import fr.uga.iut2.genconf.modele.Utilisateur;
import fr.uga.iut2.genconf.vue.shared.IHM;
import fr.uga.iut2.genconf.vue.gui.rootage.Roots;
import fr.uga.iut2.genconf.vue.shared.dto.*;

import java.util.*;
import java.util.concurrent.CountDownLatch;


public class GUI extends IHM {
    Controleur controleur;
    private final CountDownLatch eolBarrier;

    private final VuePrincipale vuePrincipale;
    private final VueDefaut VueDefaut;
    private final VueCreationConference vueCreationConf;
    private final VueCreationSession vueCreationSession;
    //private final VueModificationConference vueModificationConference;
    private final VueCreationUtilisateur vueCreationUtilisateur;
    private final VueCreationCommunication vueCreationCommunication;
    private final VueAssocierArticle vueAssocierArticle;
    private final VueCreationSpeaker vueCreationSpeaker;
    private final VueCreationChairman vueCreationChairman;
    private final VueEtat vueEtat;
    private final VueAffichageConference vueAffichageConference;
    private final VueAffichageSession vueAffichageSession;
    private final VueAffichageCommunication vueAffichageCommunication;
    private final VueAffichageUtilisateurs vueAffichageUtilisateurs;
    private final VueAffichageUtilisateur vueAffichageUtilisateur;

    public GUI(Controleur controleur) {
        this.controleur = controleur;
        // initialisé à 1 pour attendre l'évènement correspondant à la fin de vie de GUI
        this.eolBarrier = new CountDownLatch(1);

        // création de l'interface
        this.vueEtat = new VueEtat(this);
        this.vueCreationConf = new VueCreationConference(controleur, this);
        this.vueCreationSession = new VueCreationSession(controleur, this);
        this.vueCreationUtilisateur = new VueCreationUtilisateur(controleur, this);
        this.vueCreationCommunication = new VueCreationCommunication(controleur,this);
        this.vueCreationSpeaker = new VueCreationSpeaker(controleur, this);
        this.vueCreationChairman = new VueCreationChairman(controleur, this);
        this.vueAssocierArticle = new VueAssocierArticle(controleur, this);
        this.VueDefaut = new VueDefaut(this, null);
        //this.vueModificationConference = new VueModificationConference(controleur, this);
        this.vueAffichageConference = new VueAffichageConference(controleur, this);
        this.vueAffichageSession = new VueAffichageSession(controleur, this);
        this.vueAffichageCommunication = new VueAffichageCommunication(controleur, this);
        this.vueAffichageUtilisateurs = new VueAffichageUtilisateurs(controleur, this);
        this.vueAffichageUtilisateur = new VueAffichageUtilisateur(controleur, this);

        this.vuePrincipale = new VuePrincipale(this);
        this.vuePrincipale.ajouterVue(this.vueEtat, Roots.VUE_ETAT);
        //this.vuePrincipale.ajouterVue(this.vueModificationConference, Roots.VUE_MODIFICATION_CONFERENCE);
        this.vuePrincipale.ajouterVue(this.VueDefaut, Roots.VUE_DEFAUT);
        this.vuePrincipale.ajouterVue(this.vueCreationConf, Roots.VUE_CREATION_CONF);
        this.vuePrincipale.ajouterVue(this.vueCreationSession, Roots.VUE_CREATION_SESSION);
        this.vuePrincipale.ajouterVue(this.vueCreationUtilisateur, Roots.VUE_CREATION_USER);
        this.vuePrincipale.ajouterVue(this.vueCreationCommunication, Roots.VUE_CREATION_COMMUNICATION);
        this.vuePrincipale.ajouterVue(this.vueCreationSpeaker, Roots.VUE_CREATION_SPEAKER);
        this.vuePrincipale.ajouterVue(this.vueCreationChairman, Roots.VUE_CREATION_CHAIRMAN);
        this.vuePrincipale.ajouterVue(this.vueAssocierArticle, Roots.VUE_ASSOCIER_ARTICLE);
        this.vuePrincipale.ajouterVue(this.vueAffichageConference, Roots.VUE_AFFICHAGE_CONFERENCE);
        this.vuePrincipale.ajouterVue(this.vueAffichageSession, Roots.VUE_AFFICHAGE_SESSION);
        this.vuePrincipale.ajouterVue(this.vueAffichageCommunication, Roots.VUE_AFFICHAGE_COMMUNICATION);
        this.vuePrincipale.ajouterVue(this.vueAffichageUtilisateurs, Roots.VUE_AFFICHAGE_UTILISATEURS);
        this.vuePrincipale.ajouterVue(this.vueAffichageUtilisateur, Roots.VUE_AFFICHAGE_UTILISATEUR);
        this.vuePrincipale.afficherVue(Roots.VUE_ETAT);
    }

//-----  Éléments du dialogue  -------------------------------------------------

    protected void actionCreerConference() {
        this.controleur.gererDialogue(Commande.CREER_CONFERENCE);
    }

    protected void actionCreerUtilisateur() {
        this.controleur.gererDialogue(Commande.CREER_UTILISATEUR);
    }

    protected void actionTerminer() {
        this.controleur.gererDialogue(Commande.QUITTER);
    }

    protected void creerUtilisateur(Optional<NouvelUtilisateur> nouvelUtilisateur) {
        this.vuePrincipale.afficherVue(Roots.VUE_ETAT);
        nouvelUtilisateur.ifPresentOrElse(
                this.controleur::creerUtilisateur,
                () -> this.vueEtat.setEtat("")
        );
    }

    protected void creerConference(Optional<NouvelleConference> nlleConf) {
        this.vuePrincipale.afficherVue(Roots.VUE_ETAT);
        nlleConf.ifPresentOrElse(
                this.controleur::creerConference,
                () -> this.vueEtat.setEtat("")
        );
    }
    protected void creerSession(String nomConference, Optional<NouvelleSession> nlleSession) {
        this.vuePrincipale.afficherVue(Roots.VUE_ETAT);
        nlleSession.ifPresentOrElse((result) ->
                this.controleur.creerSession(nomConference, result),
                () -> this.vueEtat.setEtat("")
        );
    }
    protected void creerCommunication(String nomConference, String nomSession, Optional<NouvelleCommunication> nlleCommunication) {
        this.vuePrincipale.afficherVue(Roots.VUE_ETAT);
        nlleCommunication.ifPresentOrElse((result) ->
                        this.controleur.creerCommunication(nomConference, nomSession,result),
                () -> this.vueEtat.setEtat("")
        );
    }
    protected void creerSpeaker(String nomConference, String nomSession, String nomCommunication, Optional<NouveauSpeaker> nvSpeaker) {
        this.vuePrincipale.afficherVue(Roots.VUE_ETAT);
        nvSpeaker.ifPresentOrElse((result) ->
                        this.controleur.ajouterSpeaker(nomConference, nomSession, nomCommunication,result),
                () -> this.vueEtat.setEtat("")
        );
    }


//-----  Implémentation des méthodes abstraites  -------------------------------

    @Override
    public void afficherInterface() {
        this.vuePrincipale.afficher();
        this.vuePrincipale.afficherVue(Roots.VUE_DEFAUT); //
        this.controleur.consulterConferences(); //appel au controleur pour charger les conférences


        // On attend que GUI ait fini avant de rendre la main au contrôleur
        // (c'est à dire au moment de l'appel de `fermerInterface`)
        try {
            this.eolBarrier.await();
        }
        catch (InterruptedException exc) {
            System.err.println("Erreur d'exécution de l'interface.");
            System.err.flush();
        }
    }

    @Override
    public void fermerInterface() {
        this.vuePrincipale.fermer();

        // On notifie la fin de vie de GUI pour rendre la main au contrôleur
        this.eolBarrier.countDown();
    }

    @Override
    public void informerUtilisateur(final String msg, final boolean succes) {
        this.vueEtat.setEtat(msg + (succes ? " [Création ok]" : " [ÉCHEC]"));
        this.vuePrincipale.afficherVue(Roots.VUE_ETAT);
    }

    @Override
    public String saisirUtilisateur(Set<String> emailsUtilisateursConnus) {
        this.vuePrincipale.afficherVue(Roots.VUE_CREATION_USER);
        return null;
        //TODO
    }

    @Override
    public String saisirConference(Set<String> nomsConferencesConnues) {
        return null;
        //TODO
    }

    @Override
    public String saisirSession(Set<String> nomsSessionsConnues) {
        return null;
        //TODO
    }

    @Override
    public String saisirCommunication(Set<String> nomsCommunicationsConnues) {
        return null;
        //TODO
    }

    @Override
    public void ajouterSpeaker(Conference conference, Session session, Communication communication) {
        this.vueCreationSpeaker.setModel(communication);
        this.vuePrincipale.afficherVue(Roots.VUE_CREATION_SPEAKER);
    }

    @Override
    public void ajouterChairman(Conference conference, Session session) {
        this.vueCreationChairman.setModel(session);
        this.vuePrincipale.afficherVue(Roots.VUE_CREATION_CHAIRMAN);
    }

    @Override
    public void associerVideoCommunication(Conference conference, Session session, Communication communication) {

    }

    @Override
    public void associerArticleCommunication(Conference conference, Session session, Communication communication) {
        this.vueAssocierArticle.setModel(communication);
        this.vuePrincipale.afficherVue(Roots.VUE_ASSOCIER_ARTICLE);
    }

    @Override
    public void associerSalleSession(Conference conference, Session session) {

    }

    @Override
    public void donnerDroitsCorrespondant(Conference conference, Session session, Communication communication) {

    }

    @Override
    public void donnerDroitAdminConf(Conference conference) {

    }

    @Override
    public void definirStatusConference(Conference conference) {
        //this.vueModificationConference.setModel(conference);
        //this.vuePrincipale.afficherVue(Roots.VUE_MODIFICATION_CONFERENCE);
    }

    @Override
    public void saisirNouvelUtilisateur() {
        this.vuePrincipale.afficherVue(Roots.VUE_CREATION_USER);
    }

    @Override
    public void saisirNouvelleConference(final Set<String> nomsExistants) {
        this.vueCreationConf.setModel(nomsExistants);
        this.vuePrincipale.afficherVue(Roots.VUE_CREATION_CONF);
    }

    @Override
    public void saisirNouvelleSession(Set<String> nomsSessionsConnues, Conference conference) {
        this.vueCreationSession.setModel(conference);
        this.vuePrincipale.afficherVue(Roots.VUE_CREATION_SESSION);
    }

    @Override
    public void saisirNouvelleCommunication(Set<String> nomsCommunicationsConnues, Conference conference, Session session) {
        this.vueCreationCommunication.setModel(session);
        this.vuePrincipale.afficherVue(Roots.VUE_CREATION_COMMUNICATION);
    }

    @Override
    public void afficherUtilisateur(Utilisateur utilisateur) {
        this.vueAffichageUtilisateur.setModel(utilisateur);
        this.vuePrincipale.afficherVue(Roots.VUE_AFFICHAGE_UTILISATEUR);
    }

    @Override
    public void afficherUtilisateurs(Map<String, Utilisateur> utilisateurs) {
        this.vueAffichageUtilisateurs.setModel(utilisateurs);
        this.vuePrincipale.afficherVue(Roots.VUE_AFFICHAGE_UTILISATEURS);


    }

    @Override
    public void afficherConference(Conference conference) {
        this.vueAffichageConference.setModel(conference);
        this.vuePrincipale.afficherVue(Roots.VUE_AFFICHAGE_CONFERENCE);
    }

    @Override
    public void afficherSession(Session session) {
        this.vueAffichageSession.setModel(session);
        this.vuePrincipale.afficherVue(Roots.VUE_AFFICHAGE_SESSION);
    }

    @Override
    public void afficherCommunication(Communication communication) {
        this.vueAffichageCommunication.setModel(communication);
        this.vuePrincipale.afficherVue(Roots.VUE_AFFICHAGE_COMMUNICATION);
    }
    @Override
    public void afficherConferences(Map<String, Conference> conferences) {
        this.VueDefaut.setConferences(conferences);
        this.vuePrincipale.afficherVue(Roots.VUE_DEFAUT);
    }

    @Override
    public void afficherSessions(Map<String, Session> sessions) {

    }



    @Override
    public void afficherCommunications(Map<String, Communication> conferences) {

    }

}
