package xyz.auriium.komponent.stats.map;

import io.leangen.geantyref.TypeToken;
import xyz.auriium.komponent.stats.map.Key;

import java.util.Optional;

public class TypedObject {

    private final Object object;
    private final TypeToken<?> token;

    public TypedObject(Object object, TypeToken<?> token) {
        this.object = object;
        this.token = token;
    }

    public <T> Optional<T> query(Key<T> key) {
        if (key.getToken().getType().equals(token.getType())) {
            return (Optional<T>) Optional.of(object);
        }

        return Optional.empty();
    }


}
