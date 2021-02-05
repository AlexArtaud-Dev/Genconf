package fr.uga.iut2.genconf.vue.cli;

import fr.uga.iut2.genconf.controleur.Controleur;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.function.Function;

import fr.uga.iut2.genconf.modele.Communication;
import fr.uga.iut2.genconf.modele.Conference;
import fr.uga.iut2.genconf.modele.Session;
import fr.uga.iut2.genconf.modele.Utilisateur;
import fr.uga.iut2.genconf.modele.enums.StatusConference;

import fr.uga.iut2.genconf.vue.cli.rootage.Commande;
import fr.uga.iut2.genconf.vue.shared.IHM;
import fr.uga.iut2.genconf.vue.shared.dto.*;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * La classe CLI est responsable des interactions avec l'utilisa·teur/trice en
 * mode texte.
 * <p>
 * C'est une classe qui n'est associée à aucun état : elle ne contient aucun
 * attribut d'instance.
 * <p>
 * Aucun méthode de cette classe n'est pas censée modifier ses paramètres,
 * c'est pourquoi les paramètres des méthodes sont tous marqués comme `final`.
 *
 */
public class CLI extends IHM {

    /**
     * Nombre maximum d'essais pour la lecture d'une saisie utilisa·teur/trice.
     */
    private static final int MAX_ESSAIS = 3;
    private final Controleur controleur;

    public CLI(Controleur controleur) {
        this.controleur = controleur;
    }

//-----  Éléments d'affichage  -------------------------------------------------

    @Override
    public void afficherConference(Conference conference) {
        CLI.afficher(conference.toString());
    }

    @Override
    public void afficherSession(Session session) {
        CLI.afficher(session.toString());
    }

    @Override
    public void afficherCommunication(Communication communication) {
        CLI.afficher(communication.toString());
    }

    @Override
    public void afficherUtilisateur(Utilisateur utilisateur) {
        CLI.afficher(utilisateur.toString());
    }

    @Override
    public void afficherUtilisateurs(Map<String, Utilisateur> utilisateurs) {
        utilisateurs.values().forEach(this::afficherUtilisateur);
    }

    @Override
    public void afficherConferences(Map<String, Conference> conferences) {
        conferences.values().forEach(this::afficherConference);
    }

    @Override
    public void afficherSessions(Map<String, Session> sessions) {
        sessions.values().forEach(this::afficherSession);
    }

    @Override
    public void afficherCommunications(Map<String, Communication> conferences) {
        conferences.values().forEach(this::afficherCommunication);
    }

    @Override
    public void definirStatusConference(Conference conference) {
        StatusConference status = Inputs.StatusType(conference);

        this.controleur.changerStatusConference(conference.getNom(), status);
    }

//-----  Implémentation des méthodes abstraites  -------------------------------

    @Override
    public final void afficherInterface() {
        Commande cmd;
        do {
            cmd = Inputs.SaisirCommande();
            controleur.gererDialogue(cmd);
        } while (cmd != Commande.QUITTER);
    }

    @Override
    public final void fermerInterface() {
        // rien à faire ici
    }

    @Override
    public final void informerUtilisateur(final String msg, final boolean succes) {
        CLI.afficher((succes ? "[OK]" : "[KO]") + " " + msg);
    }

    @Override
    public void saisirNouvelUtilisateur() {
        NouvelUtilisateur infos = Inputs.SaisirUtilisateur("Saisie d'un nouvel utilisateur.ice");
        controleur.creerUtilisateur(infos);
    }

    @Override
    public String saisirUtilisateur(Set<String> emailsUtilisateursConnus) {
        CLI.afficher("Saisie d'une adresse email d'utilisateur·ice :");
        return CLI.lireNom(emailsUtilisateursConnus,false );
    }

    @Override
    public String saisirConference(Set<String> nomsConferencesConnues) {
        CLI.afficher("Saisir le nom d'une conférence :");
        return CLI.lireNom(nomsConferencesConnues, false);
    }

    @Override
    public String saisirSession(Set<String> nomsSessionsConnues) {
        CLI.afficher("Saisir le nom d'une session :");
        return CLI.lireNom(nomsSessionsConnues, false);
    }

    @Override
    public String saisirCommunication(Set<String> nomsCommunicationsConnues) {
        CLI.afficher("Saisir le nom d'une communication :");
        return CLI.lireNom(nomsCommunicationsConnues, false);
    }

    @Override
    public void saisirNouvelleConference(final Set<String> nomsExistants) {
        NouvelleConference infos = Inputs.NouvelleConference(nomsExistants);
        controleur.creerConference(infos);
    }

    @Override
    public void saisirNouvelleSession(Set<String> nomsSessionsConnues, Conference conference) {
        NouvelleSession infos = Inputs.NouvelleSession(nomsSessionsConnues, conference);
        controleur.creerSession(conference.getNom(), infos);
    }

    @Override
    public void saisirNouvelleCommunication(Set<String> nomsCommunicationConnues, Conference conference, Session session) {
        NouvelleCommunication infos = Inputs.SaisirNouvelleCommunication(nomsCommunicationConnues, conference, session);
        controleur.creerCommunication(conference.getNom(), session.getNom(), infos);
    }
    @Override
    public void ajouterSpeaker(Conference conference, Session session, Communication communication) {
        NouveauSpeaker infos = Inputs.AjouterSpeaker(communication.getNom(), communication.getSpeakers().keySet());
        controleur.ajouterSpeaker(conference.getNom(), session.getNom(), communication.getNom(), infos);
    }
    @Override
    public void ajouterChairman(Conference conference, Session session) {
        String email = Inputs.AjouterChairman(session);

        controleur.ajouterChairManSession(conference.getNom(), session.getNom(), email);
    }

    @Override
    public void associerVideoCommunication(Conference conference, Session session, Communication communication){
        String lien = Inputs.AssocierVideoCommunication(communication);
        this.controleur.associerVideoCommunication(conference.getNom(), session.getNom(), communication.getNom(), lien);//
    }

    @Override
    public void associerArticleCommunication(Conference conference, Session session, Communication communication) {
        NouvelArticle infos = Inputs.AssocierArticleCommunication(communication);
        this.controleur.associerArticleCommunication(conference.getNom(), session.getNom(), communication.getNom(), infos );
    }

    @Override
    public void associerSalleSession(Conference conference, Session session) {
        String token = Inputs.AssocierSalleSession(conference, session);

        controleur.associerSalleSession(conference.getNom(), session.getNom(), token);
    }

    @Override
    public void donnerDroitsCorrespondant(Conference conference, Session session, Communication communication) {
        String email = Inputs.DonnerDroitsCorrespondant(communication);

        this.controleur.donnerDroitsCorrespondant(conference.getNom(), session.getNom(), communication.getNom(), email);
    }

    @Override
    public void donnerDroitAdminConf(Conference conference) {
        String email = Inputs.DonnerDroitAdminConf(conference);

        this.controleur.donnerDroitAdminConf(conference.getNom(), email);
    }

    //-----  Primitives d'affichage  -----------------------------------------------

    /**
     * Construit le synopsis des commandes.
     *
     * @return Une chaîne de caractères contenant le synopsis des commandes.
     */
    static String synopsisCommandes() {
        StringBuilder builder = new StringBuilder();

        for (Commande cmd: Commande.values()) {
            builder.append("  ");  // légère indentation
            builder.append(cmd.synopsis());
            builder.append(System.lineSeparator());
        }

        return builder.toString();
    }

    /**
     * Affiche un message à l'attention de l'utilisa·teur/trice.
     *
     * @param msg Le message à afficher.
     */
    static void afficher(final String msg) {
        System.out.println(msg);
        System.out.flush();
    }

//-----  Primitives de lecture  ------------------------------------------------

    /**
     * Essaie de lire l'entrée standard avec la fonction d'interprétation.
     * <p>
     * En cas d'erreur, la fonction essaie au plus {@value #MAX_ESSAIS} fois de
     * lire l'entrée standard.
     *
     * @param <T> Le type de l'élément lu une fois interprété.
     *
     * @param parseFunction La fonction d'interprétation: elle transforme un
     *     token de type chaîne de caractère un un objet de type T.
     *     <p>
     *     La fonction doit renvoyer l'option vide en cas d'erreur, et une
     *     option contenant l'objet interprété en cas de succès.
     *     <p>
     *     La fonction d'interprétation est responsable d'afficher les messages
     *     d'erreur et de guidage utilisa·teur/trice.
     *
     * @return L'interprétation de la lecture de l'entrée standard.
     */
    static <T> T lireAvecErreurs(final Function<String, Optional<T>> parseFunction) {
        Optional<T> result = Optional.empty();
        Scanner in = new Scanner(System.in);
        String token;

        for (int i = 0; i < CLI.MAX_ESSAIS && result.isEmpty(); ++i) {
            token = in.nextLine();
            result = parseFunction.apply(token);
        }
        return result.orElseThrow(() -> new Error("Erreur de lecture (" + CLI.MAX_ESSAIS + " essais infructueux)."));
    }

    /**
     * Interprète un token entier non signé comme une {@link Commande}.
     *
     * @param token La chaîne de caractère à interpréter.
     *
     * @return Une option contenant la {@link Commande} en cas de succès,
     *     l'option vide en cas d'erreur.
     */
    static Optional<Commande> parseCommande(final String token) {
        Optional<Commande> result;
        try {
            int cmdId = Integer.parseUnsignedInt(token);  // may throw NumberFormatException
            Commande cmd = Commande.valueOfCode(cmdId);  // may throw IllegalArgumentException
            result = Optional.of(cmd);
        }
        // NumberFormatException est une sous-classe de IllegalArgumentException
        catch (IllegalArgumentException ignored) {
            CLI.afficher("Choix non valide : merci d'entrer un identifiant existant.");
            result = Optional.empty();
        }
        return result;
    }

    static Optional<Boolean> parseBooleen(final String token) {
        Optional<Boolean> resultat;
        if (token.compareTo("true") == 0 || token.compareTo("false") == 0) {
            resultat = Optional.of(Boolean.parseBoolean(token));
        }
        else {
            resultat = Optional.empty();
        }
        return resultat;
    }

    /**
     * Interprète un token comme une chaîne de caractère et vérifie que la
     * chaîne n'existe pas déjà.
     *
     * @param token La chaîne de caractère à interpréter.
     *
     * @param nomsConnus L'ensemble de chaîne de caractères qui ne sont plus
     *     disponibles.
     *
     * @return Une option contenant la chaîne de caractère en cas de succès,
     *     l'option vide en cas d'erreur.
     */
    private static Optional<String> parseNouveauNom(final String token, final Set<String> nomsConnus) {
        Optional<String> result;
        if (nomsConnus.contains(token)) {
            CLI.afficher("Le nom existe déjà dans l'application.");
            result = Optional.empty();
        } else {
            result = Optional.of(token);
        }
        return result;
    }

    /**
     * Interprète un token comme une chaîne de caractère et vérifie que la
     * chaîne existe déjà.
     *
     * @param token La chaîne de caractère à interpréter.
     *
     * @param nomsConnus L'ensemble de chaîne de caractères valides.
     *
     * @return Une option contenant la chaîne de caractère en cas de succès,
     *     l'option vide en cas d'erreur.
     */
    private static Optional<String> parseNomExistant(final String token, final Set<String> nomsConnus) {
        Optional<String> result;
        if (nomsConnus.contains(token)) {
            result = Optional.of(token);
        } else {
            CLI.afficher("Le nom n'existe pas dans l'application.");
            result = Optional.empty();
        }
        return result;
    }

    /**
     * Lit sur l'entrée standard un nom en fonction des noms connus.
     *
     * @param nomsConnus L'ensemble des noms connus dans l'application.
     *
     * @param nouveau Le nom lu doit-il être un nom connu ou non.
     *     Si {@code true}, le nom lu ne doit pas exister dans
     *     {@code nomConnus}; sinon le nom lu doit exister dans
     *     {@code nomsConnus}.
     *
     * @return Le nom saisi par l'utilisa·teur/trice.
     */
    static String lireNom(final Set<String> nomsConnus, final boolean nouveau) {
        if (nouveau) {
            if (!nomsConnus.isEmpty()) {
                CLI.afficher("Les noms suivants ne sont plus disponibles :");
                CLI.afficher("  " + String.join(", ", nomsConnus) + ".");
            }
            return CLI.lireAvecErreurs(token -> CLI.parseNouveauNom(token, nomsConnus));
        } else {
            assert !nomsConnus.isEmpty();
            CLI.afficher("Choisir un nom parmi les noms suivants :");
            CLI.afficher("  " + String.join(", ", nomsConnus) + ".");

            return CLI.lireAvecErreurs(token -> CLI.parseNomExistant(token, nomsConnus));
        }
    }

    /**
     * Lit sur l'entrée standard un nom.
     *
     * @return Le nom saisi par l'utilisa·teur/trice.
     */
    static String lireNom() {
        return CLI.lireNom(Collections.EMPTY_SET, true);
    }

    /**
     * Interprète un token comme une chaîne de caractère et vérifie que la
     * chaîne est une adresse mél valide.
     *
     * @param token La chaîne de caractère à interpréter.
     *
     * @return Une option contenant la chaîne de caractère si token est une
     *     adresse mél valide, l'option vide en cas d'erreur.
     */
    private static Optional<String> parseEmail(final String token) {
        Optional<String> result;
        EmailValidator validator = EmailValidator.getInstance(false, false);
        if (validator.isValid(token)) {
            result = Optional.of(token.toLowerCase());
        } else {
            CLI.afficher("L'adresse email n'est pas valide.");
            result = Optional.empty();
        }
        return result;
    }

    /**
     * Lit une adresse mél.
     *
     * @return L'adresse mail saisie par l'utilisa·teur/trice.
     */

    static String lireEmail(String message){
        CLI.afficher(message);
        return CLI.lireAvecErreurs(CLI::parseEmail);
    }
    /**
     * Interprète un token comme une {@link LocalDate}.
     *
     * @param token La chaîne de caractère à interpréter.
     *
     * @param apres Si l'option contient une valeur, la date lue doit être
     *     ultérieure à {@code apres}; sinon aucune contrainte n'est présente.
     *
     * @return Une option contenant la {@link LocalDate} en cas de succès,
     *     l'option vide en cas d'erreur.
     */
    private static Optional<LocalDate> parseDate(final String token, final Optional<LocalDate> apres, final Optional<LocalDate> avant) {
        try {
            LocalDate date = LocalDate.parse(token);

            if (apres.isPresent()) {
                if(apres.get().isAfter(date) && !apres.get().isEqual(date)){
                    CLI.afficher("La date saisie n'est pas ultérieure ou égale à " + apres.get().toString());
                    return Optional.empty();
                }
            }

            if (avant.isPresent()) {
                if(avant.get().isBefore(date) && !avant.get().isEqual(date))
                {
                    CLI.afficher("La date saisie n'est pas antérieure " + apres.get().toString());
                    return Optional.empty();
                }
            }

            return Optional.of(date);
        }
        catch (DateTimeParseException ignored) {
            CLI.afficher("La date saisie n'est pas valide.");
            return Optional.empty();
        }
    }

    /**
     * Lit une date au format ISO-8601.
     *
     * @param apres Si l'option contient une valeur, la date lue doit être
     *     ultérieure à {@code apres}; sinon aucune contrainte n'est présente.
     *
     * @see java.time.format.DateTimeFormatter#ISO_LOCAL_DATE
     *
     * @return La date saisie par l'utilisa·teur/trice.
     */


    private static LocalDate lireDate(final Optional<LocalDate> apres) {
        CLI.afficher("Saisir une date au format YYYY-MM-DD:");
        apres.ifPresent(date -> CLI.afficher("La date doit être ultérieure " + date.toString()));
        return CLI.lireAvecErreurs(token -> CLI.parseDate(token, apres,Optional.empty()));
    }

    private static LocalDate lireDate(final Optional<LocalDate> apres, final Optional<LocalDate> avant) {
        CLI.afficher("Saisir une date au format YYYY-MM-DD:");
        apres.ifPresent(date -> CLI.afficher("La date doit être ultérieure ou égale à " + date.toString()));
        avant.ifPresent(date->CLI.afficher("La date doit être antérieure ou égale à "+ date.toString()));
        return CLI.lireAvecErreurs(token -> CLI.parseDate(token, apres, avant));
    }

    /**
     * Lit une date au format ISO-8601.
     * <p>
     * Alias pour {@code lireDate(Optional.empty())}.
     *
     * @return La date saisie par l'utilisa·teur/trice.
     *
     * @see #lireDate(java.util.Optional)
     */
    static LocalDate lireDate() {
        return CLI.lireDate(Optional.empty(),Optional.empty());
    }


    /**
     * Lit une date au format ISO-8601.
     * <p>
     * Alias pour {@code lireDate(Optional.of(apres))}.
     *
     * @param apres La date saisie doit être ultérieure à {@code apres}.
     *
     * @return La date saisie par l'utilisa·teur/trice.
     *
     * @see #lireDate(java.util.Optional)
     */
    static LocalDate lireDate(final LocalDate apres) {
        return CLI.lireDate(Optional.of(apres));
    }

    static LocalDate lireDate(final LocalDate apres, final LocalDate avant) {
        return CLI.lireDate(Optional.of(apres), Optional.of(avant));
    }
}
