package com.sarality.app.datastore.query;

import com.sarality.app.datastore.Column;

import java.util.ArrayList;
import java.util.List;

/**
 * Base implementation for classes that build a query.
 *
 * @param <B> Type of Builder used to build the query.
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class BaseQueryBuilder<B extends BaseQueryBuilder<B>> {

  private final List<QueryFilter> filterList = new ArrayList<QueryFilter>();
  private final List<QueryClause> clauseList = new ArrayList<QueryClause>();

  // The type of filter. Initialized to NONE since no filter condition is defined at first.
  private FilterType filterType = FilterType.NONE;

  protected abstract B getInstance();

  /**
   * Start building a filter with the condition defined on the given column.
   *
   * @param column Column to define the filter condition on.
   * @return A query Filter Builder used to complete the filter condition.
   */
  public QueryFilterBuilder<B> where(Column column) {
    if (filterList.size() != 0 || clauseList.size() != 0) {
      throw new IllegalStateException("WHERE has already been called for this query. A query must call WHERE only " +
          "once, at the start of the clause follow by calls to AND, OR or END");
    }
    return new QueryFilterBuilder<B>(column, getInstance());
  }

  /**
   * Add an AND filter with the condition defined on the given column.
   *
   * @param column Column to define the filter condition on.
   * @return A query Filter Builder used to complete the filter condition.
   */
  public QueryFilterBuilder<B> and(Column column) {
    if (filterList.size() == 1 || clauseList.size() == 1) {
      filterType = FilterType.AND;
    }
    assertValidFilterType(FilterType.AND);
    return new QueryFilterBuilder<B>(column, getInstance());
  }

  /**
   * Add an OR filter with the condition defined on the given column.
   *
   * @param column Column to define the filter condition on.
   * @return A query Filter Builder used to complete the filter condition.
   */
  public QueryFilterBuilder<B> or(Column column) {
    if (filterList.size() == 1 || clauseList.size() == 1) {
      filterType = FilterType.OR;
    }
    assertValidFilterType(FilterType.OR);
    return new QueryFilterBuilder<B>(column, getInstance());
  }

  /**
   * Start building a filter with query clause defined by the given builder.
   *
   * @param builder Builder for the QueryClause.
   * @return The query builder with the query clause added to it.
   */
  public B where(QueryClauseBuilder builder) {
    if (filterList.size() != 0 || clauseList.size() != 0) {
      throw new IllegalStateException("WHERE has already been called for this query. A query must call WHERE only " +
          "once, at the start of the clause followed by calls to AND or OR if needed");
    }
    clauseList.add(new QueryClause(builder.getFilterList(), builder.getClauseList(), builder.getFilterType()));
    return getInstance();
  }

  /**
   * Add an AND filter with query clause defined by the given builder.
   *
   * @param builder builder Builder for the QueryClause.
   * @return The query builder with the query clause added to it.
   */
  public B and(QueryClauseBuilder builder) {
    // If we already have a clause or a filter, you can now set the filter type.
    if ((filterList.size() + clauseList.size()) == 1) {
      filterType = FilterType.AND;
    }
    assertValidFilterType(FilterType.AND);
    // Convert all existing Filters into their own clause.
    convertFiltersToClause();

    clauseList.add(new QueryClause(builder.getFilterList(), builder.getClauseList(), builder.getFilterType()));
    return getInstance();
  }

  /**
   * Add an OR filter with query clause defined by the given builder.
   *
   * @param builder builder Builder for the QueryClause.
   * @return The query builder with the query clause added to it.
   */
  public B or(QueryClauseBuilder builder) {
    // If we already have a clause or a filter, you can now set the filter type.
    if ((filterList.size() + clauseList.size()) == 1) {
      filterType = FilterType.OR;
    }
    assertValidFilterType(FilterType.OR);
    // Convert all existing Filters into their own clause.
    convertFiltersToClause();

    clauseList.add(new QueryClause(builder.getFilterList(), builder.getClauseList(), builder.getFilterType()));
    return getInstance();
  }

  protected final List<QueryFilter> getFilterList() {
    return filterList;
  }

  protected final List<QueryClause> getClauseList() {
    return clauseList;
  }

  protected final FilterType getFilterType() {
    return filterType;
  }

  void addFilter(QueryFilter filter) {
    this.filterList.add(filter);
  }

  /**
   * Add all existing Filters into their own clause.
   * <p/>
   * No-op if no filters have been added so far.
   */
  protected void convertFiltersToClause() {
    if (!filterList.isEmpty()) {
      // Take all existing filters and turn them into a clause.
      List<QueryFilter> clauseFilterList = new ArrayList<QueryFilter>();
      clauseFilterList.addAll(filterList);
      filterList.clear();
      clauseList.add(new QueryClause(clauseFilterList, new ArrayList<QueryClause>(), filterType));
    }
  }

  protected void assertValidFilterType(FilterType newFilterType) {
    if (filterList.size() == 0 && clauseList.size() == 0) {
      throw new IllegalStateException("A query filter must be defined by first calling Where and then adding other " +
          "filters by calling AND or OR");
    }

    if (this.filterType != newFilterType) {
      throw new IllegalStateException("Invalid combination of AND and OR filters. Cannot define an "
          + newFilterType.name() + " Condition when all previous filter conditions use an " + filterType.name()
          + " clause");
    }
  }
}
