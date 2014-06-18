package com.sarality.app.datastore.query;

import java.util.ArrayList;
import java.util.List;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.DataStore;

/**
 * Class used to build Query to either query or update a data store.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class QueryBuilder {
  // The Datastore to query
  private final DataStore<?> store;

  // The list of columns to retrieve
  private final List<Column> columnList = Lists.of();

  // The filters that form the Where clause i.e. Column and Value pair
  private final List<QueryFilter> filterList = new ArrayList<QueryFilter>();

  // Indicated whether the filter at this positions is ANDed or ORed with the
  // previous set of filters
  private final List<Operator> operatorList = new ArrayList<Operator>();

  // Indicates in what order should the rows be returned
  private Column orderByCol;

  private Boolean orderByDesc;

  /**
   * Constructor.
   * 
   * @param store
   *          Datastore to run this query on.
   */
  public QueryBuilder(DataStore<?> store) {
    this.store = store;
    this.columnList.addAll(store.getColumns());
  }

  /**
   * Specify the columns to retrieve from the DataStore.
   * 
   * @param columns
   *          Columns to retrieve from the DataStore.
   * @return Query builder with the set of columns defined.
   */
  public QueryBuilder withColumns(Column... columns) {
    if (columns != null) {
      columnList.clear();
      for (Column column : columns) {
        columnList.add(column);
      }
    }
    return this;
  }

  /**
   * Specify the columns to retrieve from the DataStore.
   * 
   * @param columns
   *          Columns to retrieve from the DataStore.
   * @return Query builder with the set of columns defined.
   */
  public QueryBuilder withColumns(List<Column> columns) {
    if (columns != null) {
      columnList.clear();
      for (Column column : columns) {
        columnList.add(column);
      }
    }
    return this;
  }

  /**
   * @return Datastore to run this query on.
   */
  public final DataStore<?> getDataStore() {
    return store;
  }

  /**
   * Start generating a filter for the query that needs to be run.
   * <p>
   * This must be followed by a call to one of the FilterTagert.operator(value) methods to add the filter to the
   * QueryBuilder.
   * 
   * @param column
   *          Column to define the filter on.
   * @return The FilterTarget from which the resulting QueryBuilder can be generated.
   */
  public FilterTarget where(Column column) {
    return new FilterTarget(this, column, null);
  }

  /**
   * Define another filter for the query by doing an intersection with the existing filters already defined for the
   * query.
   * <p>
   * This must followed by a call to one of the FilterTagert.operator(value) methods to add the filter to the
   * QueryBuilder.
   * 
   * @param column
   *          Column to define the filter on.
   * @return The FilterTarget from which the resulting QueryBuilder can be generated.
   */
  public FilterTarget and(Column column) {
    return new FilterTarget(this, column, Operator.AND);
  }

  /**
   * Define another filter for the query by doing a union with the existing filters already defined for the query.
   * <p>
   * This must followed by a call to one of the FilterTagert.operator(value) methods to add the filter to the
   * QueryBuilder.
   * 
   * @param column
   *          Column to define the filter on.
   * @return The FilterTarget from which the resulting QueryBuilder can be generated.
   */
  public FilterTarget or(Column column) {
    return new FilterTarget(this, column, Operator.OR);
  }

  /**
   * Builds the query to allow rows to be returned in a order defined by a specific column
   * 
   * @param column
   *          Column to define the filter on.
   * @return The current QueryBuilder
   */
  public QueryBuilder orderBy(Column column) {
    orderByCol = column;
    return this;
  }

  /**
   * The order in which the sorting needs to be done
   * 
   * @param order
   *          Ascending or descending order
   * @return The current QueryBuilder
   */
  public QueryBuilder orderByDesc(Boolean order) {
    orderByDesc = order;
    return this;
  }
  
  /**
   * @return Query object defined by the builder.
   */
  public Query build() {
    StringBuilder builder = new StringBuilder();
    List<String> valueList = Lists.of();

    boolean isFirst = true;
    boolean isSimpleEqualityFilter = false;
    int ctr = 0;
    for (QueryFilter filter : filterList) {
      if (!isFirst) {
        Operator operator = operatorList.get(ctr);
        isSimpleEqualityFilter = isSimpleEqualityFilter & (operator == Operator.AND);
        builder.append(" ").append(operator.name());
      }
      isSimpleEqualityFilter = isSimpleEqualityFilter & (filter.getOperation() == Operator.EQUALS);

      builder.append(" (").append(filter.getColumn().getName()).append(" ")
          .append(filter.getOperation().getSqlString());
      String value = filter.getValue();
      if (value != null) {
        builder.append(" ? ");
        valueList.add(value);
      }
      builder.append(")");
      isFirst = false;
      ctr++;
    }

    if (valueList.isEmpty()) {
      valueList = null;
    }

    String whereClause = builder.toString();
    if (whereClause.length() == 0) {
      whereClause = null;
    }

    String orderByStr = orderByCol.toString();
    if(orderByDesc)
      orderByStr = orderByStr.concat(" DESC");
    else
      orderByStr = orderByStr.concat(" ASC");

    return new Query(columnList, whereClause, valueList, orderByStr);
  }

  /**
   * Package protected Method called by FilterTarget to add an intersection Filter to the Builder
   * 
   * @param column
   *          Column to set the filter on
   * @param operator
   *          Operator that defines whether the column is Null or Not Null
   */
  void addAndFilter(Column column, Operator operator) {
    if (operator != Operator.IS_NULL && operator != Operator.IS_NOT_NULL) {
      throw new IllegalArgumentException("Cannot add AND filter for Operator " + operator + " without a value");
    }
    filterList.add(new QueryFilter(column, operator, null));
    operatorList.add(Operator.AND);
  }

  /**
   * Package protected Method called by FilterTarget to add a union Filter to the Builder
   * 
   * @param column
   *          Column to set the filter on
   * @param operator
   *          Operator that defines whether the column is Null or Not Null
   */
  void addOrFilter(Column column, Operator operator) {
    if (operator != Operator.IS_NULL && operator != Operator.IS_NOT_NULL) {
      throw new IllegalArgumentException("Cannot add OR filter for Operator " + operator + " without a value");
    }
    filterList.add(new QueryFilter(column, operator, null));
    operatorList.add(Operator.OR);
  }

  /**
   * Package protected Method called by FilterTarget to add a union Filter to the Builder
   * 
   * @param column
   *          Column to set the filter on
   * @param operator
   *          Operator that defines the type if filter i.e. =, <, > etc
   * @param value
   *          String form the value to be used by the where clause filter
   */
  void addAndFilter(Column column, Operator operator, String value) {
    if (operator == Operator.IS_NULL || operator == Operator.IS_NOT_NULL) {
      throw new IllegalArgumentException("Cannot add AND filter for Operator " + operator + " along with a value");
    }
    filterList.add(new QueryFilter(column, operator, value));
    operatorList.add(Operator.AND);
  }

  /**
   * Package protected Method called by FilterTarget to add an intersection Filter to the Builder
   * 
   * @param column
   *          Column to set the filter on
   * @param operator
   *          Operator that defines the type if filter i.e. =, <, > etc
   * @param value
   *          String form the value to be used by the where clause filter
   */
  void addOrFilter(Column column, Operator operator, String value) {
    if (operator == Operator.IS_NULL || operator == Operator.IS_NOT_NULL) {
      throw new IllegalArgumentException("Cannot add OR filter for Operator " + operator + " along with a value");
    }
    filterList.add(new QueryFilter(column, operator, value));
    operatorList.add(Operator.OR);
  }
}
