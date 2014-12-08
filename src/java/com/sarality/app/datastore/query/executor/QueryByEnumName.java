package com.sarality.app.datastore.query.executor;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.data.EnumData;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.DataStore;
import com.sarality.app.datastore.query.FilterValue;
import com.sarality.app.datastore.query.Operator;
import com.sarality.app.datastore.query.QueryBuilder;

import java.util.List;

/**
 * Class that queries the associated data based on an Enum Name.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class QueryByEnumName<A, E extends EnumData<E>> implements AssociatedDataQuery<List<A>, List<E>> {

  private final DataStore<A> dataStore;
  private final QueryBuilder queryBuilder;
  private final Column enumColumn;

  public QueryByEnumName(DataStore<A> dataStore, QueryBuilder queryBuilder, Column enumColumn) {
    this.dataStore = dataStore;
    this.queryBuilder = queryBuilder;
    this.enumColumn = enumColumn;
  }

  @Override
  public List<A> execute(List<E> enumList) {
    List<FilterValue<?>> joinValues = Lists.of();
    for (E enumValue : enumList) {
      joinValues.add(FilterValue.of(enumValue.getEnumName()));
    }
    try {
      QueryBuilder newQueryBuilder;
      if (queryBuilder.getNumFilters() == 0) {
        newQueryBuilder = queryBuilder.where(enumColumn, Operator.IN, joinValues);
      } else {
        newQueryBuilder = queryBuilder.and(enumColumn, Operator.IN, joinValues);
      }
      dataStore.open();
      return dataStore.query(newQueryBuilder.build());
    } finally {
      dataStore.close();
    }
  }
}
