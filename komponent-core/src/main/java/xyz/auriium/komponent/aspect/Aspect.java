package xyz.auriium.komponent.aspect;

/**
 * Aspect of a component. Generally should only be one aspect per type, as fine-tuned cascading values are better off
 * being calculated by the generic Stat aspect.
 *
 * @param <T> type
 */
public interface Aspect<T> {

    T aspect();

}
