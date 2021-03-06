package com.sarality.app.datastore.query;

import android.util.Pair;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.DataStore;
import com.sarality.app.datastore.DataStoreRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to build Query to either query or update a data store.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class QueryBuilder {

  // The DataStore to query
  private final DataStore<?> store;

  // The list of columns to retrieve
  private final List<Column> columnList = Lists.of();

  // The type of filter. Initialized to NONE since no filter condition is defined at first.
  private FilterType filterType = FilterType.NONE;

  // The filters that form the Where clause i.e. Column and Value pair
  private final List<QueryFilter> filterList = new ArrayList<QueryFilter>();

  // Indicates in what order should the rows be returned
  private final List<Pair<Column, Boolean>> orderByColumnList = new ArrayList<Pair<Column, Boolean>>();

  /**
   * Constructor.
   *
   * @param store Datastore to run this query on.
   */
  public QueryBuilder(DataStore<?> store) {
    this.store = store;
    this.columnList.addAll(store.getColumns());
  }

  /**
   * Name based Constructor.
   *
   * @param dataStoreName Name of the DataStore.
   * @param registry Registry that the Query is registered with.
   */
  public QueryBuilder(String dataStoreName, DataStoreRegistry registry) {
    this(registry.getDataStore(dataStoreName));
  }

  /**
   * @return Datastore to run this query on.
   */
  public final DataStore<?> getDataStore() {
    return store;
  }

  public int getNumFilters() {
    return filterList.size();
  }

  /**
   * Adds a where clause to the query.
   *
   * @param column Column to filter on
   * @param operator Operator to apply on the column.
   * @return A QueryBuilder with the given filter.
   */
  public QueryBuilder where(Column column, Operator operator) {
    if (filterList.size() > 0) {
      throw new IllegalStateException("Cannot call Where multiple times. It must be called only once and at "
          + "start of the filter defintion");
    }
    assertValidFilter(column, operator, null);
    filterList.add(new QueryFilter(column, operator));
    return this;
  }

  /**
   * Adds a where clause to the query.
   *
   * @param column Column to filter on
   * @param operator Operator to apply on the column.
   * @param value The value to be compared with column value.
   * @return A QueryBuilder with the given filter.
   */
  public QueryBuilder where(Column column, Operator operator, FilterValue<?> value) {
    if (filterList.size() > 0) {
      throw new IllegalStateException("Cannot call Where multiple times. It must be called only once and at "
          + "start of the filter defintion");
    }
    assertValidFilter(column, operator, value);
    filterList.add(new QueryFilter(column, operator, value));
    return this;
  }

  /**
   * Adds a where clause to the query, only applicable for "IN" clause
   *
   * @param column Column to filter on
   * @param operator Operator to apply on the column.
   * @param valueList The list of values to be compared with column value.
   * @return A QueryBuilder with the given filter.
   */
  public QueryBuilder where(Column column, Operator operator, List<FilterValue<?>> valueList) {
    if (filterList.size() > 0) {
      throw new IllegalStateException("Cannot call Where multiple times. It must be called only once and at "
          + "start of the filter defintion");
    }
    if (operator != Operator.IN) {
      throw new IllegalArgumentException("List<FilterValue> only applicable for Operator IN and not for " + operator +
          " with values " + valueList);
    }
    if (valueList == null) {
      throw new IllegalArgumentException("Value cannot be Null for Operator IN");
    }

    filterList.add(new QueryFilter(column, operator, valueList));
    return this;
  }

  /**
   * Adds an AND filter to the Query.
   * <p/>
   * All previous filters must also be AND filters.
   *
   * @param column Column to filter on
   * @param operator Operator to apply on the column.
   * @return A QueryBuilder with the given filter.
   */
  public QueryBuilder and(Column column, Operator operator) {
    assertValidFilter(column, operator, null);
    assertCanAddFilter(FilterType.AND);
    filterList.add(new QueryFilter(column, operator));
    return this;
  }

  /**
   * Adds an AND filter to the Query.
   * <p/>
   * All previous filters must also be AND filters.
   *
   * @param column Column to filter on
   * @param operator Operator to apply on the column.
   * @param value The value to be compared with column value.
   * @return A QueryBuilder with the given filter.
   */
  public QueryBuilder and(Column column, Operator operator, FilterValue<?> value) {
    assertValidFilter(column, operator, value);
    assertCanAddFilter(FilterType.AND);
    filterList.add(new QueryFilter(column, operator, value));
    return this;
  }

  /**
   * Adds an AND filter to the query.
   * <p/>
   * All previous filters must also be AND filters.
   *
   * @param column Column to filter on
   * @param operator Operator to apply on the column.
   * @param valueList The list of values to be compared with column value.
   * @return A QueryBuilder with the given filter.
   */
  public QueryBuilder and(Column column, Operator operator, List<FilterValue<?>> valueList) {
    if (operator != Operator.IN) {
      throw new IllegalArgumentException("List<FilterValue> only applicable for Operator IN");
    }
    if (valueList == null) {
      throw new IllegalArgumentException("Value cannot be Null for Operator IN");
    }
    assertCanAddFilter(FilterType.AND);
    filterList.add(new QueryFilter(column, operator, valueList));
    return this;
  }

  /**
   * Adds an OR filter to the Query.
   * <p/>
   * All previous filters must also be OR filters.
   *
   * @param column Column to filter on
   * @param operator Operator to apply on the column.
   * @return A QueryBuilder with the given filter.
   */
  public QueryBuilder or(Column column, Operator operator) {
    assertValidFilter(column, operator, null);
    assertCanAddFilter(FilterType.OR);
    filterList.add(new QueryFilter(column, operator));
    return this;
  }

  /**
   * Adds an OR filter to the Query.
   * <p/>
   * All previous filters must also be OR filters.
   *
   * @param column Column to filter on
   * @param operator Operator to apply on the column.
   * @param value The value to be compared with column value.
   * @return A QueryBuilder with the given filter.
   */
  public QueryBuilder or(Column column, Operator operator, FilterValue<?> value) {
    assertValidFilter(column, operator, value);
    assertCanAddFilter(FilterType.OR);
    filterList.add(new QueryFilter(column, operator, value));
    return this;
  }

  /**
   * Adds an OR filter to the query.
   * <p/>
   * All previous filters must also be OR filters.
   *
   * @param column Column to filter on
   * @param operator Operator to apply on the column.
   * @param valueList The list of values to be compared with column value.
   * @return A QueryBuilder with the given filter.
   */
  public QueryBuilder or(Column column, Operator operator, List<FilterValue<?>> valueList) {
    if (filterList.size() > 0) {
      throw new IllegalStateException("Cannot call Where multiple times. It must be called only once and at "
          + "start of the filter defintion");
    }
    if (operator != Operator.IN) {
      throw new IllegalArgumentException("List<FilterValue> only applicable for Operator IN");
    }
    if (valueList == null) {
      throw new IllegalArgumentException("Value cannot be Null for Operator IN");
    }
    assertCanAddFilter(FilterType.OR);
    filterList.add(new QueryFilter(column, operator, valueList));
    return this;
  }

  /**
   * Builds the query to allow rows to be returned in a order defined by a specific column
   *
   * @param column Column to define the filter on.
   * @param ascendingOrder Indicates whether data needs to sorted on ascending order of column value.
   * @return The current QueryBuilder
   */
  public QueryBuilder orderBy(Column column, boolean ascendingOrder) {
    orderByColumnList.add(new Pair<Column, Boolean>(column, ascendingOrder));
    return this;
  }

  /**
   * @return Query object defined by the builder.
   */
  public Query build() {
    List<String> valueList = Lists.of();

    StringBuilder whereClauseBuilder = new StringBuilder();
    boolean isFirst = true;
    for (QueryFilter filter : filterList) {
      if (!isFirst) {
        whereClauseBuilder.append(" ").append(filterType.getSqlString());
      }

      whereClauseBuilder.append(" (").append(filter.getColumn().getName()).append(" ")
          .append(filter.getOperation().getSqlString());
      if (filter.getOperation() == Operator.IN) {
        whereClauseBuilder.append(buildInQuery(filter, valueList));
      } else {
        if (filter.getValue() != null) {
          String value = filter.getValue().getStringValue(filter.getColumn());
          whereClauseBuilder.append(" ? ");
          if (filter.getOperation().equals(Operator.LIKE)) {
            value = "%" + value + "%";
          }
          valueList.add(value);
        }
      }
      whereClauseBuilder.append(")");
      isFirst = false;
    } // Query Filter

    if (valueList.isEmpty()) {
      valueList = null;
    }

    String whereClause = whereClauseBuilder.toString();
    if (whereClause.length() == 0) {
      whereClause = null;
    }

    StringBuilder orderByClause = new StringBuilder();
    boolean processedFirstColumn = false;
    for (Pair<Column, Boolean> orderByColumn : orderByColumnList) {
      if (processedFirstColumn) {
        orderByClause.append(", ");
      }
      orderByClause.append(orderByColumn.first.getName()).append(" ");
      if (orderByColumn.second) {
        orderByClause.append(" ASC");
      } else {
        orderByClause.append(" DESC");
      }
      processedFirstColumn = true;
    } // Order By Column

    return new Query(columnList, filterType, filterList, orderByColumnList, whereClause, valueList,
        orderByClause.toString());
  }

  /**
   * Asserts whether a Filter Operation and Value pair is valid or not.
   * <p/>
   * Performs a simple validation to check if the given Operator supports passing of values or not.
   *
   * @param column Column for the filter.
   * @param operator Operator for the filter.
   * @param value Value for the filter.
   */
  private void assertValidFilter(Column column, Operator operator, FilterValue<?> value) {
    if (value != null && (operator == Operator.IS_NULL || operator == Operator.IS_NOT_NULL)) {
      throw new IllegalArgumentException("Cannot add filter for Operator " + operator + " with a value "
          + value.getStringValue(column));
    } else if (value == null && (operator != Operator.IS_NULL && operator != Operator.IS_NOT_NULL)) {
      throw new IllegalArgumentException("Cannot add filter for Operator " + operator + " without a value");
    }
  }

  private void assertCanAddFilter(FilterType newFilterType) {
    if (filterList.size() == 0) {
      throw new IllegalStateException("A query filter must be defined by first calling Where and then adding other"
          + " filters by calling AND or OR");
    }

    if (filterList.size() == 1) {
      this.filterType = newFilterType;
    }

    if (this.filterType != newFilterType) {
      throw new IllegalStateException("Invalid combination of AND and OR filters. Cannot define an "
          + newFilterType.name() + " Condition when all previous filter conditions use an " + filterType.name()
          + " clause");
    }
  }

  /**
   * Builds the Query String for Clause "IN"
   *
   * @param filter : QueryFilter carrying the list of values
   * @param valueList : ValueList to be added to
   * @return : WhereClause
   */
  private String buildInQuery(QueryFilter filter, List<String> valueList) {
    List<FilterValue<?>> values = filter.getValues();
    StringBuilder whereClauseBuilder = new StringBuilder(" (");
    int count = 0;
    for (FilterValue<?> value : values) {
      String stringValue = value.getStringValue(filter.getColumn());
      valueList.add(stringValue);
      whereClauseBuilder.append("?");
      count++;
      if (values.size() != count) {
        whereClauseBuilder.append(",");
      }
    }
    whereClauseBuilder.append(")");
    return whereClauseBuilder.toString();
  }
}
