package com.sarality.app.view.datasource;

import java.util.ArrayList;
import java.util.List;

import com.sarality.app.data.DataObject;
import com.sarality.app.datastore.db.Table;
import com.sarality.app.datastore.db.TableRegistry;
import com.sarality.app.datastore.query.Query;

/**
 * DataSource that uses a Table to load its data.
 *
 * @param <T> Data returned by the DataSource and the underlying Table.
 * @author abhideep@ (Abhideep Singh)
 */
public class TableDataSource<T extends DataObject<T>> implements DataSource<List<T>> {
  private final Table<T> table;
  private final Query query;
  private final List<T> dataList = new ArrayList<T>();

  public TableDataSource(Table<T> table, Query query) {
    this.table = table;
    this.query = query;
  }

  public TableDataSource(String tableName, TableRegistry registry, Query query) {
    this((Table<T>) registry.getTable(tableName), query);
  }

  public TableDataSource(String tableName, Query query) {
    this(tableName, TableRegistry.getGlobalInstance(), query);
  }

  @Override
  public boolean isStaticDataSet() {
    return false;
  }

  @Override
  public void loadData() {
    dataList.clear();

    table.open();
    List<T> returnedDataList = table.query(query);
    dataList.addAll(returnedDataList);

    table.close();
  }

  @Override
  public List<T> getData() {
    return dataList;
  }
}
