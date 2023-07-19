package org.csu.mypetstore.utils;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable<O extends Observer, A> {

    protected final List<O> observers;

    public Observable() {
        this.observers = new ArrayList<>(3);
    }

    @SuppressWarnings("unchecked")
    public void notifyObservers(A argument, Action action) {
        observers.forEach(observer -> observer.update(argument, action));
    }
}
