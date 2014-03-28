package com.sarality.app.view.datasource;

import java.util.ArrayList;
import java.util.List;

import com.sarality.app.db.Table;
import com.sarality.app.db.Query;

public class TableDataSource<T> implements DataSource<T> {
  private final Table<T> table;
  private final Query query;
  private final List<T> dataList = new ArrayList<T>();
  
  public TableDataSource(Table<T> table, Query query) {
    this.table = table;
    this.query = query;
  }

  @Override
  public boolean isStaticDataSet() {
    return false;
  }

  @Override
  public void loadData() {
    dataList.clear();
    dataList.addAll(table.query(query));
  }

  @Override
  public List<T> getData() {
    return dataList;
  }
}