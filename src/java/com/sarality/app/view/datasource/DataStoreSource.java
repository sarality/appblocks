package com.sarality.app.view.datasource;

import java.util.ArrayList;
import java.util.List;

import com.sarality.app.data.DataObject;
import com.sarality.app.datastore.DataStore;
import com.sarality.app.datastore.query.Query;

public class DataStoreSource<T extends DataObject<T>> implements DataSource<T> {

  private final DataStore<T> store;
  private final Query query;
  private final List<T> dataList = new ArrayList<T>();

  public DataStoreSource(DataStore<T> store, Query query) {
    this.store = store;
    this.query = query;
  }

  @Override
  public boolean isStaticDataSet() {
    return false;
  }

  @Override
  public void loadData() {
    dataList.clear();

    store.open();
    List<T> returnedDataList = store.query(query);
    dataList.addAll(returnedDataList);

    store.close();
  }

  @Override
  public List<T> getData() {
    return dataList;
  }
}
