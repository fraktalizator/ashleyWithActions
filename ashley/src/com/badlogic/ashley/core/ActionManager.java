package com.badlogic.ashley.core;

import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

class ActionManager {
    private final Array<EntityAction> Actions = new Array<>(false, 16);
    private final ImmutableArray<EntityAction> immutableActions = new ImmutableArray<>(Actions);
    private final ObjectMap<Class<?>, EntityAction> ActionsByClass = new ObjectMap<>();
    private final ActionManager.ActionListener listener;

    public ActionManager(ActionListener listener) {
        this.listener = listener;
    }

    public void addAction(EntityAction Action){
        Class<? extends EntityAction> ActionType = Action.getClass();
        EntityAction oldAction = getAction(ActionType);

        if (oldAction != null) {
            removeAction(oldAction);
        }

        Actions.add(Action);
        ActionsByClass.put(ActionType, Action);
        listener.actionAdded(Action);
    }

    public void removeAction(EntityAction Action){
        if(Actions.removeValue(Action, true)) {
            ActionsByClass.remove(Action.getClass());
            listener.actionRemoved(Action);
        }
    }

    public void removeAllActions() {
        while(Actions.size > 0) {
            removeAction(Actions.first());
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends EntityAction> T getAction(Class<T> ActionType) {
        return (T) ActionsByClass.get(ActionType);
    }

    public ImmutableArray<EntityAction> getActions() {
        return immutableActions;
    }

    interface ActionListener{
        void actionAdded(EntityAction Action);
        void actionRemoved(EntityAction Action);
    }
}
