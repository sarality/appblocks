package com.sarality.app.datastore.query;

/**
 * A builder for a QueryClause.
 * <p/>
 * Used to build sub clauses of a Query Builder.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class QueryClauseBuilder extends BaseQueryBuilder<QueryClauseBuilder> {

  @Override
  protected QueryClauseBuilder getInstance() {
    return this;
  }
}
