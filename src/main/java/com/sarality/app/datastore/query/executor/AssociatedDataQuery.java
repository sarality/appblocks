package com.sarality.app.datastore.query.executor;

/**
 * Interface for all Executors that execute a Query for Associated Data.
 *
 * @param <A> Type of Associated Data object
 * @param <I> Input data needed to execute the query.
 */
public interface AssociatedDataQuery<A, I> {

  public A execute(I input);
}
