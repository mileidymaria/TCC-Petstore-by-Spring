package org.csu.mypetstore.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Generic observer inspired by Java Generics and Collection by {@literal Naftalin & Wadler}.
 * @param <O> Observer
 * @param <A> Argument type
 */
public abstract class Observable<O extends Observer, A> {

    /**
     * Observer with filters
     */
    private final Map<Filter, List<O>> observers;

    public Observable() {
        this.observers = new HashMap<>(3);
    }

    public void addObserver(Filter filter, List<O> observers) {
        this.observers.put(filter, observers);
    }

    /**
     * Notify observers.
     */
    @SuppressWarnings("unchecked")
    public void notifyObservers(A argument, Action action, Filter filter) {
        for (Map.Entry<Filter, List<O>> entry : observers.entrySet()) {
            if (filter.equals(entry.getKey())){
                List<O> observers = entry.getValue();
                observers.forEach(observer -> observer.update(argument, action));
            }
        }
    }
}