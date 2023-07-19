package org.csu.mypetstore.utils;

public interface Observer<A> {

    void update(A argument, Action action);
}