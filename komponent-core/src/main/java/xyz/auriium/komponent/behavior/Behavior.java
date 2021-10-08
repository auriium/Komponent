package xyz.auriium.komponent.behavior;

import xyz.auriium.komponent.behavior.event.ComponentEvent;

/**
 * Behavior of a component. Usually there should only be one behavior per type.
 *
 * @param <T>
 */
public interface Behavior<T extends ComponentEvent> {

    void behavior(T input);

}
