package xyz.auriium.komponent.aspect;

import xyz.auriium.komponent.stats.Stats;
import xyz.auriium.komponent.stats.map.Key;

public class StaGenericDamage implements StatAspect {

    public static final Key<Integer> DAMAGE = new Key<>("generic_damage", Integer.class);

    private final StatAspect delegate;

    public StaGenericDamage(StatAspect delegate) {
        this.delegate = delegate;
    }

    @Override
    public Stats aspect() {
        Stats.Mutable mutable =  delegate.aspect().copy();

        mutable.update(DAMAGE, old -> old + 5);

        return mutable.make();
    }
}
