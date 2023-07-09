package org.csu.mypetstore.common;

/**
 * Observer.
 *
 * @param <A> argument
 */
public interface Observer<A, ACT> {

    void update(A argument, ACT Action );
}