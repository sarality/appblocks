package com.sarality.app.datastore.query.executor;

import com.sarality.app.datastore.query.Query;

/**
 * Implementation of QueryExecutor that returns the same static data.
 * <p/>
 *
 * The query passed into the execute method is ignored.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class StaticDataQueryExecutor<T> implements QueryExecutor<T> {

  private final T data;

  public StaticDataQueryExecutor(T data) {
    this.data = data;
  }

  @Override
  public T execute(Query query) {
    return data;
  }
}
