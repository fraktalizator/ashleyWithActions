package com.badlogic.ashley.core;

abstract public class EntityAction {
    private Engine engine;

    public EntityAction () {
    }

    /**
     * Called when this EntitySystem is added to an {@link Engine}.
     * @param engine The {@link Engine} this system was added to.
     */
    public void addedToEngine (Engine engine) {
    }

    /**
     * Called when this EntitySystem is removed from an {@link Engine}.
     * @param engine The {@link Engine} the system was removed from.
     */
    public void removedFromEngine (Engine engine) {
    }

    /**
     * The act method runs defined action on an entity
     */
    abstract public void act(Entity entity);

    /** @return engine instance the system is registered to.
     * It will be null if the system is not associated to any engine instance. */
    public Engine getEngine () {
        return engine;
    }

    final void addedToEngineInternal(Engine engine) {
        this.engine = engine;
        addedToEngine(engine);
    }

    final void removedFromEngineInternal(Engine engine) {
        this.engine = null;
        removedFromEngine(engine);
    }
}
