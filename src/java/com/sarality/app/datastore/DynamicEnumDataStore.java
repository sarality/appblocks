package com.sarality.app.datastore;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.data.EnumData;
import com.sarality.app.data.EnumDataRegistry;
import com.sarality.app.datastore.query.FilterValue;
import com.sarality.app.datastore.query.Operator;
import com.sarality.app.datastore.query.Query;
import com.sarality.app.datastore.query.QueryBuilder;
import com.sarality.app.datastore.query.QueryFilter;

import java.util.List;

/**
 * A DataStore that dynamically loads EnumData from a persistent DataStore if it is not found in the EnumDataRegistry.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class DynamicEnumDataStore<T extends EnumData<T>> implements DataStore<T> {

  private final DataStore<T> dataStore;
  private final Column enumNameColumn;
  private final Class<T> enumClass;
  private final EnumDataRegistry registry;

  public DynamicEnumDataStore(DataStore<T> dataStore, Column enumNameColumn, Class<T> enumClass) {
    this.dataStore = dataStore;
    this.enumNameColumn = enumNameColumn;
    this.enumClass = enumClass;
    this.registry = EnumDataRegistry.getGlobalInstance();
  }

  public List<T> queryAllByName(List<String> enumNameList) {
    List<T> enumList = Lists.of();
    List<FilterValue<?>> filterValueList = Lists.of();
    for (String enumName : enumNameList) {
      T value = registry.valueOf(enumClass, enumName);
      if (value != null) {
        enumList.add(value);
      } else {
        filterValueList.add(FilterValue.of(enumName));
      }
    }
    if (!filterValueList.isEmpty()) {
      Query query = new QueryBuilder(dataStore).where(enumNameColumn, Operator.IN, filterValueList).build();
      try {
        dataStore.open();
        List<T> dbEnumList = dataStore.query(query);
        if (enumList != null) {
          enumList.addAll(dbEnumList);
        }
      } finally {
        dataStore.close();
      }
    }
    return enumList;
  }

  public T queryByName(String enumName) {
    T value = registry.valueOf(enumClass, enumName);
    if (value == null) {
      Query query = new QueryBuilder(dataStore)
          .where(enumNameColumn, Operator.EQUALS, FilterValue.of(enumName)).build();
      try {
        dataStore.open();
        List<T> enumList = dataStore.query(query);
        if (enumList == null || enumList.isEmpty()) {
          return null;
        }
        if (enumList.size() > 1) {
          throw new IllegalStateException("Multiple Enums found with name " + enumName + " for " + enumClass);
        }
        value = enumList.get(0);
      } finally {
        dataStore.close();
      }
    }
    return value;
  }

  @Override
  public String getName() {
    return dataStore.getName();
  }

  @Override public void open() {
    // Do nothing - The data store is opened and closed in the query method if needed.
  }

  @Override
  public void close() {
    // Do nothing - The data store is opened and closed in the query method if needed.
  }

  @Override
  public List<Column> getColumns() {
    return dataStore.getColumns();
  }

  @Override
  public List<T> query(Query query) {
    List<QueryFilter> queryFilterList = query.getFilterList();
    if (queryFilterList == null || queryFilterList.size() != 1) {
      throw new IllegalArgumentException("Must specify one and only one filter for querying the EnumDataStore for "
          + enumClass);
    }
    QueryFilter filter = queryFilterList.get(0);
    Column queryColumn = filter.getColumn();
    if (queryColumn.getName() != enumNameColumn.getName()) {
      throw new IllegalArgumentException("Cannot query the EnumDataStore for " + enumClass + " with a query on " +
          "column " + queryColumn.getName() + " Only a query by Name is allowed on Column " + enumNameColumn.getName());
    }
    if (filter.getOperation() == Operator.EQUALS) {
      String enumName = getEnumName(filter.getValue());
      return Lists.of(queryByName(enumName));
    } else if (filter.getOperation() == Operator.IN) {
      List<FilterValue<?>> filterValueList = filter.getValues();
      List<String> enumNameList = Lists.of();
      for (FilterValue<?> filterValue : filterValueList) {
        enumNameList.add(getEnumName(filterValue));
      }
      return queryAllByName(enumNameList);
    } else {
      throw new IllegalArgumentException("Only IN and EQUALS filters supported by EnumDataStore for " + enumClass);
    }
  }

  private String getEnumName(FilterValue<?> filterValue) {
    if (filterValue == null || filterValue.getValue() == null) {
      throw new IllegalArgumentException("Cannot query by EnumDataStore for " + enumClass + " with null enum name");
    }
    return (String) filterValue.getValue();
  }
}
