package com.sarality.app.datastore.query.executor;

import com.sarality.app.data.DataTransformer;
import com.sarality.app.datastore.query.Query;

/**
 * QueryExecutor that transforms the results from the given QueryExecutor and returns the transformed value.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class TransformResultQueryExecutor<I, T> implements QueryExecutor<T> {

  private final QueryExecutor<I> queryExecutor;
  private final DataTransformer<I, T> transformer;

  public TransformResultQueryExecutor(QueryExecutor<I> queryExecutor, DataTransformer<I, T> transformer) {
    this.queryExecutor = queryExecutor;
    this.transformer = transformer;
  }

  @Override
  public T execute(Query query) {
    I input = queryExecutor.execute(query);
    return transformer.transform(input);
  }
}
