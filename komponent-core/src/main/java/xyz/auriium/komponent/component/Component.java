package xyz.auriium.komponent.component;

import xyz.auriium.komponent.aspect.Aspect;
import xyz.auriium.komponent.behavior.event.ComponentEvent;

public interface Component {

    void behavior(ComponentEvent event);
    <T> T aspect(Class<Aspect<T>> aspect);

}
