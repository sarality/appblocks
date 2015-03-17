package com.sarality.app.config;

import java.util.List;

import com.sarality.app.datastore.db.Table;

public class TableIsEmpty implements AppStateValue<Boolean> {

  private final Table<?> table;

  public TableIsEmpty(Table<?> table) {
    this.table = table;
  }

  @Override
  public Boolean getValue() {
    try {
      table.open();
      List<?> dataList = table.query(null);
      return dataList.isEmpty();
    } finally {
      table.close();
    }
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
