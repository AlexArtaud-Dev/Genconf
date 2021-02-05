package fr.uga.iut2.genconf.modele.enums;

import java.util.Optional;

public enum TypeSession {

    Keynote,
    Article,
    Tutorial;

    public static Optional<TypeSession> parseFrom(final String token) {
        switch (token.toLowerCase()){
            case "keynote":
                return Optional.of(TypeSession.Keynote);
            case "article":
                return Optional.of(TypeSession.Article);
            case "tutorial":
                return Optional.of(TypeSession.Tutorial);
            default:
                return Optional.empty();
        }
    }
}
