package com.sarality.app.datastore.query.executor;

import com.sarality.app.datastore.query.Query;

/**
 * Interface for all classes that execute a Query on a DataStore.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface QueryExecutor<T> {

  /**
   * Execute the query.
   *
   * @param query Query to execute on the DataStore, {@code null} is no query is needed.
   * @return The result of the execution of the Query.
   */
  public T execute(Query query);
}
