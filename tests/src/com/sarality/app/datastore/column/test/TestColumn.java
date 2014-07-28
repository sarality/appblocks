package com.sarality.app.datastore.column.test;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnSpec;

class TestColumn implements Column {

  private final String name;
  private final ColumnSpec spec;

  TestColumn(String name, ColumnSpec spec) {
    this.name = name;
    this.spec = spec;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public ColumnSpec getSpec() {
    return spec;
  }
}
