package xyz.auriium.komponent.stats;

import io.leangen.geantyref.TypeToken;
import xyz.auriium.komponent.stats.map.Key;
import xyz.auriium.komponent.stats.map.TypedObject;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

//TODO make code even more performant
public class StBase implements Stats {

    private final Map<String, TypedObject> stats;

    StBase(Map<String, TypedObject> stats) {
        this.stats = stats;
    }

    StBase() {
        this.stats = Map.of();
    }

    @Override
    public <T> T query(Key<T> key) {
        String identifier = key.getIdentifier();

        TypedObject nullable = stats.get(identifier);

        if (nullable == null) {
            throw new NoSuchElementException(String.format("Key %s is missing from stat map!", identifier));
        }

        var opt = nullable.query(key);

        if (opt.isEmpty()) {
            throw new IllegalTypeException(String.format("Key %s was of unexpected type for raw key in stat map!", identifier));
        }

        return opt.get();
    }

    @Override
    public Mutable copy() {
        return null;
    }

    public static class Mutable implements Stats.Mutable {

        private final Map<String, TypedObject> mutable;

        public Mutable(Map<String, TypedObject> mutable) {
            this.mutable = mutable;
        }

        @Override
        public <T> T query(Key<T> key) {
            String identifier = key.getIdentifier();

            TypedObject nullable = mutable.get(identifier);

            if (nullable == null) {
                throw new NoSuchElementException(String.format("Key %s is missing from stat map!", identifier));
            }

            var opt = nullable.query(key);

            if (opt.isEmpty()) {
                throw new IllegalTypeException(String.format("Key %s was of unexpected type for raw key in stat map!", identifier));
            }

            return opt.get();
        }

        @Override
        public <T> void set(Key<T> key, T object) {

            String identifier = key.getIdentifier();
            TypedObject nullable;
            if ((nullable = mutable.get(identifier)) != null && nullable.query(key).isEmpty()) {
                throw new IllegalTypeException(String.format("Key %s was present but of incorrect type in stat map!", identifier));
            }

            mutable.put(identifier, new TypedObject(object, key.getToken()));
        }

        @Override
        public <T> void update(Key<T> key, Function<T, T> function) {
            String identifier = key.getIdentifier();
            TypeToken<T> token = key.getToken();

            mutable.compute(identifier, (a,b) -> {

                if (b != null) {

                    Optional<T> opt;
                    if ((opt = b.query(key)).isEmpty()) {
                        throw new IllegalTypeException(String.format("Key %s was present but of incorrect type in stat map!", identifier));
                    }

                    return new TypedObject(function.apply(opt.get()), token);
                }

                return new TypedObject(function.apply(null), token);

            });
        }

        @Override
        public Stats make() {
            return new StBase(Map.copyOf(mutable));
        }
    }
}
