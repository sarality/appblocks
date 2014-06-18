package com.sarality.app.config;

import java.util.List;

import com.sarality.app.datastore.db.Table;
import com.sarality.app.datastore.query.Query;

public class TableIsEmpty implements AppStateValue<Boolean> {

  private final Table<?> table;
  
  public TableIsEmpty(Table<?> table) {
    this.table = table;
  }

  @Override
  public Boolean getValue() {
    table.open();
    List<?> dataList = table.query(null);
    return dataList.isEmpty();
  }

  @Override
  public boolean isEditable() {
    return false;
  }

  @Override
  public void setValue(Boolean value) {
    // No op
  }
}
