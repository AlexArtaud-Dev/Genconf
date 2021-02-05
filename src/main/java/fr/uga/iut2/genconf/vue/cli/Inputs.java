package fr.uga.iut2.genconf.vue.cli;

import fr.uga.iut2.genconf.modele.Communication;
import fr.uga.iut2.genconf.modele.Conference;
import fr.uga.iut2.genconf.modele.Session;
import fr.uga.iut2.genconf.modele.enums.StatusConference;
import fr.uga.iut2.genconf.modele.enums.TypeCommunication;
import fr.uga.iut2.genconf.modele.enums.TypeSession;
import fr.uga.iut2.genconf.vue.cli.rootage.Commande;
import fr.uga.iut2.genconf.vue.shared.dto.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;

public class Inputs {

    static Commande SaisirCommande() {
        CLI.afficher("===== GenConf: Générateur Site Conférence =====");
        CLI.afficher(CLI.synopsisCommandes());
        CLI.afficher("===============================================");
        CLI.afficher("Saisir l'identifiant de l'action choisie :");
        return CLI.lireAvecErreurs(CLI::parseCommande);
    }

    static NouvelUtilisateur SaisirUtilisateur(String message) {
        String email, nom, prenom;

        CLI.afficher(message);
        email = CLI.lireEmail("Saisir l'email");
        CLI.afficher("Saisir le nom :");
        nom = CLI.lireNom();
        CLI.afficher("Saisir le prénom :");
        prenom = CLI.lireNom();

        return new NouvelUtilisateur(email, nom, prenom);
    }

    static NouvelleConference NouvelleConference(final Set<String> nomsConferencesConnus) {
        String nom;
        LocalDate dateDebut, dateFin;
        NouvelUtilisateur admin;
        StatusConference status;

        CLI.afficher("== Saisie d'une nouvelle conférence ==");
        CLI.afficher("Saisir le nom de la conférence :");
        nom = CLI.lireNom(nomsConferencesConnus, true);
        CLI.afficher("Date de début: ");
        dateDebut = CLI.lireDate();
        CLI.afficher("Date de fin: ");
        dateFin = CLI.lireDate(dateDebut);

        CLI.afficher("Saisir les informations à propos de l'administrateur·ice de la conférence.");
        CLI.afficher("Un·e nouvel·le utilisateur·ice sera créé·e si nécessaire.");
        admin = SaisirUtilisateur("==Saisie d'un administrateur==");

        CLI.afficher("Saisir le statut de conférence : (Presentiel/Distanciel)");
        status = CLI.lireAvecErreurs(StatusConference::parseFrom);

        return new NouvelleConference(nom, dateDebut, dateFin, admin, status);
    }

    static NouvelleSession NouvelleSession(final Set<String> nomSessionsConnues, Conference conference) {
        String nom;
        LocalDate dateDebut, dateFin;
        TypeSession type;
        CLI.afficher("== Saisie d'une nouvelle session ==");
        CLI.afficher("Saisir le nom de la session :");
        nom = CLI.lireNom(nomSessionsConnues,true);
        CLI.afficher("Saisir le type de session :\n (Possibilités : Keynote, Article, Tutorial)");
        type = CLI.lireAvecErreurs(TypeSession::parseFrom);
        CLI.afficher("Saisir la date de début :");
        dateDebut = CLI.lireDate(conference.getDateDebut());
        CLI.afficher("Saisir la date de fin");
        dateFin = CLI.lireDate(dateDebut, conference.getDateFin());

        return new NouvelleSession(nom, type, dateDebut, dateFin);
    }

    static NouvelleCommunication SaisirNouvelleCommunication(final Set<String> nomsCommunicationConnues, Conference conference, Session session) {
        String nomCommunication, emailReferent;
        LocalDate dateDebut, dateFin;
        TypeCommunication type;

        CLI.afficher("== Saisie d'une nouvelle communication ==");
        CLI.afficher("Saisir le nom de la communication:");
        nomCommunication = CLI.lireNom(nomsCommunicationConnues, true);
        CLI.afficher("Date de début: ");
        dateDebut = CLI.lireDate(session.getDateDebut());
        CLI.afficher("Date de fin: ");
        dateFin = CLI.lireDate(dateDebut, session.getDateFin());
        CLI.afficher("Saisir le type de communication (keynote/article/tutorial) :");
        type = CLI.lireAvecErreurs(TypeCommunication::parseFrom);

        NouvelUtilisateur referent = SaisirUtilisateur("== Saisie d'un référent ==");

        return new NouvelleCommunication(nomCommunication, type, dateDebut, dateFin, referent);
    }

    static NouveauSpeaker AjouterSpeaker(String nomCommunication, Set<String> emailsSpeakersConnus) {
        String email;
        String nom;
        String prenom;

        CLI.afficher("==Ajout d'un·e nouveau·elle speaker à la communication "+nomCommunication+"==");
        CLI.afficher("Saisir l'adresse email du / de la nouvel·le speaker :");
        email = CLI.lireNom(emailsSpeakersConnus, true);
        CLI.afficher("Saisir le nom du / de la nouvel·le speaker :");
        nom =CLI.lireNom();
        CLI.afficher("Saisir le prénom du / de la nouvel·le speaker :");
        prenom =CLI.lireNom();

        return new NouveauSpeaker(nom, prenom, email);
    }

    static String AjouterChairman(Session session) {
        String email;

        CLI.afficher("Ajout d'un Chairman à la session "+ session.getNom());
        CLI.afficher("Saisir l'adresse email du compte genConf du futur chairman :");
        email = CLI.lireNom(session.getChairmans().keySet(), true);

        return email;
    }

    static String AssocierVideoCommunication(Communication communication) {
        String lien;
        CLI.afficher("Lien de la vidéo pour la communication "+ communication.getNom());
        lien= CLI.lireNom();

        return lien;
    }

    static NouvelArticle AssocierArticleCommunication(Communication communication) {
        String nom;
        String lienPDF;
        HashMap<String, NouvelAuteur> auteurs = new HashMap<>();

        CLI.afficher("Association d'un article à la communication "+communication.getNom());
        CLI.afficher("Saisir le nom de l'article");
        nom = CLI.lireNom();
        CLI.afficher("Saisir le lien du PDF de l'article ");
        lienPDF = CLI.lireNom();

        CLI.afficher("Saisie des auteurs");
        do {
            String nomAuteur;
            String prenomAuteur;
            String emailAuteur;
            CLI.afficher("Saisissez le nom d'un auteur");
            nomAuteur = CLI.lireNom();
            CLI.afficher("Saisissez le prénom d'un auteur");
            prenomAuteur = CLI.lireNom();
            emailAuteur = CLI.lireEmail("Saisissez l'adresse email d'un auteur");
            if (!nomAuteur.isEmpty() && !prenomAuteur.isEmpty() && !auteurs.containsKey(emailAuteur)) {
                auteurs.put(emailAuteur,new NouvelAuteur(nomAuteur, prenomAuteur, emailAuteur));
            } else {
                CLI.afficher("Les informations que vous avez saisies sont incorrectes ou sont un doublon par adresse email.. Cette saisie ne sera pas prise en compte. Vous pouvez saisir d'autres noms.");
            }
        } while (questionnerUtilisateur("Voulez-vous ajouter un autre auteur ?"));
        return new NouvelArticle(nom, lienPDF, auteurs);
    }

    static String AssocierSalleSession(Conference conference, Session session) {
        String token;

        if(conference.getStatus().equals(StatusConference.Distanciel)) {
            CLI.afficher("Saisir l'URL de la salle de réunion virtuelle de la session "+ session.getNom());
        } else {
            CLI.afficher("Saisir le nom de la salle de réunion pour la session "+ session.getNom());
        }

        token = CLI.lireNom();
        return token;
    }

    static String DonnerDroitsCorrespondant(Communication communication) {
        String email;
        email = CLI.lireEmail("Saisir l'email du nouveau  correspondant pour la communication " + communication.getNom());
        return email;
    }

    static String DonnerDroitAdminConf(Conference conference) {
        String email;
        email = CLI.lireEmail("Saisir l'email de l'admin à ajouter pour la conférence : "+ conference.getNom());
        return email;
    }

    static StatusConference StatusType(Conference conference) {
        StatusConference status;

        CLI.afficher("Choisir le nouveau statut de la conférence (présentiel, distanciel) :");
        status = CLI.lireAvecErreurs(StatusConference::parseFrom);

        return status;
    }

    static boolean questionnerUtilisateur(String message) {
        CLI.afficher(message);
        return CLI.lireAvecErreurs(CLI::parseBooleen);
    }
}
