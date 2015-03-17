package com.sarality.app.datastore.query;

import com.sarality.app.datastore.Column;

import java.util.List;

/**
 * Adds QueryFilter to a QueryClause Builder for the given column.
 *
 * @param <B> Type of Builder to which the filter is being added.
 * @author abhideep@ (Abhideep Singh)
 */
public class QueryFilterBuilder<B extends BaseQueryBuilder<B>> {

  private final Column column;
  private final B builder;

  QueryFilterBuilder(Column column, B builder) {
    this.column = column;
    this.builder = builder;
  }

  public B isNull() {
    builder.addFilter(new QueryFilter(column, Operator.IS_NULL));
    return builder;
  }

  public B isNotNull() {
    builder.addFilter(new QueryFilter(column, Operator.IS_NOT_NULL));
    return builder;
  }

  public <T> B equals(FilterValue<T> value) {
    assertNotNull(Operator.EQUALS, value);
    builder.addFilter(new QueryFilter(column, Operator.EQUALS, value));
    return builder;
  }

  public <T> B notEquals(FilterValue<T> value) {
    assertNotNull(Operator.NOT_EQUALS, value);
    builder.addFilter(new QueryFilter(column, Operator.NOT_EQUALS, value));
    return builder;
  }

  public <T> B lessThan(FilterValue<T> value) {
    assertNotNull(Operator.LESS_THAN, value);
    builder.addFilter(new QueryFilter(column, Operator.LESS_THAN, value));
    return builder;
  }

  public <T> B greaterThan(FilterValue<T> value) {
    assertNotNull(Operator.GREATER_THAN, value);
    builder.addFilter(new QueryFilter(column, Operator.GREATER_THAN, value));
    return builder;
  }

  public B in(List<FilterValue<?>> valueList) {
    assertNotNull(valueList);
    builder.addFilter(new QueryFilter(column, Operator.IN, valueList));
    return builder;
  }

  private <T> void assertNotNull(Operator operator, FilterValue<T> value) {
    if (value == null || value.getValue() == null) {
      throw new IllegalArgumentException("Cannot add " + operator + " filter for Column " + column.getName() +
          " with a null value.");
    }
  }

  private void assertNotNull(List<FilterValue<?>> values) {
    if (values == null) {
      throw new IllegalArgumentException("Cannot add IN filter for Column " + column.getName() + " with a null value.");
    }
    for (FilterValue<?> value : values) {
      if (value == null || value.getValue() == null) {
        throw new IllegalArgumentException("Cannot add andIN filter for Column " + column.getName() + " with one of " +
            "the values being null.");
      }
    }
  }
}
