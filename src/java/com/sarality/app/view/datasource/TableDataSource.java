package com.sarality.app.view.datasource;

import java.util.ArrayList;
import java.util.List;

import com.sarality.app.data.DataObject;
import com.sarality.app.datastore.db.Table;
import com.sarality.app.datastore.query.Query;

public class TableDataSource<T extends DataObject<T>> implements DataSource<T> {
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
