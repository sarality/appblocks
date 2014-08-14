package com.sarality.app.datastore.db.test;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnSpec;

/**
 * Simple Column used for testing purposes.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class TestColumn implements Column {

  private final String name;
  private final ColumnSpec spec;

  public TestColumn(String name, ColumnSpec spec) {
    this.name = name;
    this.spec = spec;
  }

  public String getName() {
    return name;
  }

  public ColumnSpec getSpec() {
    return spec;
  }
}
