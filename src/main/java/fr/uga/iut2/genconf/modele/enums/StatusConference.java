package fr.uga.iut2.genconf.modele.enums;

import java.util.Optional;

public enum StatusConference {

    Presentiel,
    Distanciel;

    public static Optional<StatusConference> parseFrom(final String token) {
        switch (token.toLowerCase()){
            case "presentiel":
                return Optional.of(StatusConference.Presentiel);
            case "distanciel":
                return Optional.of(StatusConference.Distanciel);
            default:
                return Optional.empty();
        }
    }
}
