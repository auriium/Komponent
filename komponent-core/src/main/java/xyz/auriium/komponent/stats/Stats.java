package xyz.auriium.komponent.stats;

import xyz.auriium.komponent.stats.map.Key;

import java.util.function.Function;

public interface Stats {

    /**
     * Queries for a value
     * @param key key
     * @param <T> type
     * @throws java.util.NoSuchElementException if not present
     * @return the value
     */
    <T> T query(Key<T> key);

    Mutable copy();

    interface Mutable {

        <T> T query(Key<T> key);

        /**
         * Inserts a value if not present, or sets a value if present
         * @param key key
         * @param object object
         * @param <T> type
         * @throws IllegalTypeException if stat is of different type than inserted object
         */
        <T> void set(Key<T> key, T object);

        /**
         * Updates a value using a function. Function is provided null if not present, or object if present.
         * Function can return null to remove key, or return a new value to set that value.
         *
         * @param key key
         * @param function function
         * @param <T> type
         */
        <T> void update(Key<T> key, Function<T, T> function);

        Stats make();
    }

}
