package fr.uga.iut2.genconf.vue.shared;

import fr.uga.iut2.genconf.modele.Communication;
import fr.uga.iut2.genconf.modele.Conference;
import fr.uga.iut2.genconf.modele.Session;
import fr.uga.iut2.genconf.modele.Utilisateur;

import java.util.Map;
import java.util.Set;

public abstract class IHM {

    /**
     * Rend actif l'interface Humain-machine.
     *
     * L'appel est bloquant : le contrôle est rendu à l'appelant une fois que
     * l'IHM est fermée.
     *
     */
    public abstract void afficherInterface();

    /**
     * Rend inactif l'interface Humain-machine.
     *
     */
    public abstract void fermerInterface();

    /**
     * Affiche un message d'information à l'attention de l'utilisa·teur/trice.
     *
     * @param msg Le message à afficher.
     *
     * @param succes true si le message informe d'une opération réussie, false
     *     sinon.
     */
    public abstract void informerUtilisateur(final String msg, final boolean succes);

    /**
     * Récupère les informations à propos d'un
     * {@link fr.uga.iut2.genconf.modele.Utilisateur}.
     *
     */
    public abstract String saisirUtilisateur(final Set<String> emailsUtilisateursConnus);
    public abstract String saisirConference(final Set<String> nomsConferencesConnues);
    public abstract String saisirSession(final Set<String> nomsSessionsConnues);
    public abstract String saisirCommunication(final Set<String> nomsCommunicationsConnues);
    public abstract void ajouterSpeaker(Conference conference, Session session, Communication communication);
    public abstract void ajouterChairman(Conference conference, Session session);
    public abstract void associerVideoCommunication(Conference conference, Session session, Communication communication);
    public abstract void associerArticleCommunication(Conference conference, Session session, Communication communication);
    public abstract void associerSalleSession(Conference conference, Session session);
    public abstract void donnerDroitsCorrespondant(Conference conference, Session session, Communication communication);
    public abstract void donnerDroitAdminConf(Conference conference);
    public abstract void definirStatusConference(Conference conference);

    // Post
    public abstract void saisirNouvelUtilisateur();
    public abstract void saisirNouvelleConference(final Set<String> nomsConferencesConnues);
    public abstract void saisirNouvelleSession(final Set<String> nomsSessionsConnues, Conference conference);
    public abstract void saisirNouvelleCommunication(final Set<String> nomsCommunicationsConnues, Conference conference, Session session);

    // Get
    public abstract void afficherConference(Conference conference);
    public abstract void afficherSession (Session session);
    public abstract void afficherCommunication(Communication communication);
    public abstract void afficherUtilisateur(Utilisateur utilisateur);

    // Index
    public abstract void afficherUtilisateurs(Map<String , Utilisateur> utilisateurs);
    public abstract void afficherConferences(Map<String , Conference> conferences);
    public abstract void afficherSessions(Map<String, Session> sessions);
    public abstract void afficherCommunications(Map<String, Communication> conferences);
}
