package fr.uga.iut2.genconf.vue.cli.rootage;

import java.util.HashMap;
import java.util.Map;


/**
 * L'enum Commande répertorie les actions que l'utilisa·teur/trice peut
 * entreprendre via l'IHM.
 *
 */
public enum Commande {

    QUITTER(0, "Quitter"),
    CREER_UTILISATEUR(1, "Créer un.e nouvel.le utilisateur.ice"),
    CREER_CONFERENCE(2,"Créer une nouvelle conférence"),
    CREER_SESSION(3, "Créer une nouvelle session"),
    CREER_COMMUNICATION(4, "Créer une nouvelle communication"),
    AJOUTER_SPEAKER(5, "Ajouter un.e nouveau.elle speaker à une communication"),
    AJOUTER_CHAIRMAN_SESSION(6, "Ajouter un.e chairman à une session"),
    CONSULTER_REFERENT(7, "Consulter les informations d'un.e référent.e sur une  communication"),
    CONSULTER_CONFERENCE(8, "Consulter les informations d'une conférence"),
    CONSULTER_SESSION(9, "Consulter les informations d'une session"),
    CONSULTER_COMMUNICATION(10, "Consulter les informations d'une communication"),
    CONSULTER_UTILISATEUR(11,"Consulter les informations correspondant à un utilisateur.ice"),
    CONSULTER_UTILISATEURS(12,"Consulter les informations de tous les utilisateurs.ice"),
    ASSOCIER_VIDEO_COMMUNICATION(13,"Associer une video a une communication"),
    ASSOCIER_ARTICLE_COMMUNICATION(14, "Associer un article à une communication"),
    ASSOCIER_SALLE_SESSION(15, "Associer une salle (virtuelle ou physique) à une session"),
    DONNER_DROIT_ADMIN_CONF(16,"Donner les droits d'administrateur d'une conférence à un utilisateur.ice"),
    RENDRE_CONFERENCE_PUBLIC(17, "Rendre le site d'une conférence accessible au public"),
    CHANGER_STATUT_CONFERENCE(18, "Définir le statut d'une conference entre presentiel et distanciel"),
    CHANGER_REFERENT(19, "Changer le référent.e d'une communication et lui donne les droits")
    ;

    private final int code;
    private final String description;

    private static final Map<Integer, Commande> valueByCode = new HashMap<>();

    static {
        // On initialise une fois pour la durée de vie de l'application le
        // cache de la fonction `valueOfCode`
        for (Commande cmd: Commande.values()) {
            Commande.valueByCode.put(cmd.code, cmd);
        }
    }

    /**
     * Renvoie la variante de la classe enum dont le code est donné en
     * paramètre.
     *
     * @param code Le code de la variante à retourner.
     *
     * @return La variante de la classe enum dont le code est celui spécifié.
     */
    public static final Commande valueOfCode(int code) {
        Commande result = Commande.valueByCode.get(code);
        if (result == null) {
            throw new IllegalArgumentException("Invalid code");
        }
        return result;
    }

    private Commande(int code, String description) {
        assert code >= 0;
        this.code = code;
        this.description = description;
    }

    /**
     * Renvoie le synopsis mis en forme de la commande.
     *
     * @return Une chaîne de caractères sans retour à la ligne contenant le
     *     synopsis de la commande.
     */
    public String synopsis() {
        return this.code + " — " + this.description;
    }
}
