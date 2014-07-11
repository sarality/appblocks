package com.sarality.app.datastore.query;

import java.util.ArrayList;
import java.util.List;

import android.util.Pair;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.DataStore;

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
   * @return Datastore to run this query on.
   */
  public final DataStore<?> getDataStore() {
    return store;
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
    filterList.add(new QueryFilter(column, operator, null));
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
   * Adds an AND filter to the Query.
   * <p>
   * All previous filters must also be AND filters.
   * 
   * @param column Column to filter on
   * @param operator Operator to apply on the column.
   * @return A QueryBuilder with the given filter.
   */
  public QueryBuilder and(Column column, Operator operator) {
    assertValidFilter(column, operator, null);
    assetCanAddFilter(FilterType.AND);
    filterList.add(new QueryFilter(column, operator, null));
    return this;
  }

  /**
   * Adds an AND filter to the Query.
   * <p>
   * All previous filters must also be AND filters.
   * 
   * @param column Column to filter on
   * @param operator Operator to apply on the column.
   * @param value The value to be compared with column value.
   * @return A QueryBuilder with the given filter.
   */
  public QueryBuilder and(Column column, Operator operator, FilterValue<?> value) {
    assertValidFilter(column, operator, value);
    assetCanAddFilter(FilterType.AND);
    filterList.add(new QueryFilter(column, operator, value));
    return this;
  }

  /**
   * Adds an OR filter to the Query.
   * <p>
   * All previous filters must also be OR filters.
   * 
   * @param column Column to filter on
   * @param operator Operator to apply on the column.
   * @return A QueryBuilder with the given filter.
   */
  public QueryBuilder or(Column column, Operator operator) {
    assertValidFilter(column, operator, null);
    assetCanAddFilter(FilterType.OR);
    filterList.add(new QueryFilter(column, operator, null));
    return this;
  }

  /**
   * Adds an OR filter to the Query.
   * <p>
   * All previous filters must also be OR filters.
   * 
   * @param column Column to filter on
   * @param operator Operator to apply on the column.
   * @param value The value to be compared with column value.
   * @return A QueryBuilder with the given filter.
   */
  public QueryBuilder or(Column column, Operator operator, FilterValue<?> value) {
    assertValidFilter(column, operator, value);
    assetCanAddFilter(FilterType.OR);
    filterList.add(new QueryFilter(column, operator, value));
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
      String value = filter.getValue().getStringValue(filter.getColumn());
      if (value != null) {
        whereClauseBuilder.append(" ? ");
        valueList.add(value);
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

    return new Query(columnList, filterType, filterList, orderByColumnList, 
        whereClause, valueList, orderByClause.toString());
  }

  /**
   * Asserts whether a Filter Operation and Value pair is valid or not.
   * <p>
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
    } else if (value == null && (operator != Operator.IS_NULL || operator != Operator.IS_NOT_NULL)) {
      throw new IllegalArgumentException("Cannot add filter for Operator " + operator + " without a value");
    }
  }

  private void assetCanAddFilter(FilterType newFilterType) {
    if (filterList.size() == 0) {
      throw new IllegalStateException("A query filter must be defined by first calling Where and then adding other"
          + " filters by calling AND or OR");
    }

    if (filterList.size() == 1) {
      this.filterType = newFilterType;      
    }

    if (this.filterType != newFilterType) {
      throw new IllegalStateException("Invalid combination of AND and OR filters. Cannot define an " 
          + newFilterType.name() + " Condition when all previous filter conditions use an " 
          + filterType.name() + " clause");
    }
  }
}
