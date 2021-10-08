package xyz.auriium.komponent.component;

import xyz.auriium.komponent.aspect.Aspect;

public class CpBase implements Component {
    @Override
    public void behavior(Object object) {

    }

    @Override
    public <T> T aspect(Class<Aspect<T>> aspect) {
        return null;
    }
}
