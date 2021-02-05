package fr.uga.iut2.genconf.modele.enums;

import java.util.Optional;

public enum TypeCommunication {

    Keynote,
    Article,
    Tutorial;

    public static Optional<TypeCommunication> parseFrom(final String token) {
        switch (token.toLowerCase()){
            case "keynote":
                return Optional.of(TypeCommunication.Keynote);
            case "article":
                return Optional.of(TypeCommunication.Article);
            case "tutorial":
                return Optional.of(TypeCommunication.Tutorial);
            default:
                return Optional.empty();
        }
    }
}
