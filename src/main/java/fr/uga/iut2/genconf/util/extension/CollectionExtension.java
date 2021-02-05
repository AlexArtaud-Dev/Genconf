package fr.uga.iut2.genconf.util.extension;

import java.util.Map;
import java.util.stream.Collectors;

public class CollectionExtension {
    public static String KeysToString(Map<String, ?> map) {
        return map.keySet().stream()
                .collect(Collectors.joining(", ", "{", "}"));
    }
}
