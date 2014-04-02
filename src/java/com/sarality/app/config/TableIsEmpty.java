package com.sarality.app.config;

import java.util.List;

import com.sarality.app.db.Query;
import com.sarality.app.db.Table;

public class TableIsEmpty implements AppStateValue<Boolean> {

  private final Table<?> table;
  
  public TableIsEmpty(Table<?> table) {
    this.table = table;
  }

  @Override
  public Boolean getValue() {
    table.open();
    List<?> dataList = table.query(new Query());
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
