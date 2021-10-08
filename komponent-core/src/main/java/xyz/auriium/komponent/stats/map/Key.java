package xyz.auriium.komponent.stats.map;

import io.leangen.geantyref.TypeToken;

public class Key<T> {

    private final String identifier;
    private final TypeToken<T> token;

    public Key(String identifier, TypeToken<T> token) {
        this.identifier = identifier;
        this.token = token;
    }

    public Key(String identifier, Class<T> clazz) {
        this.identifier = identifier;
        this.token = TypeToken.get(clazz);
    }

    public String getIdentifier() {
        return identifier;
    }

    public TypeToken<T> getToken() {
        return token;
    }
}
