package com.sarality.app.datastore;

import com.sarality.app.datastore.extractor.ColumnValueExtractor;

public class ColumnConfig<V> {
  private ColumnSpec spec;
  private ColumnValueExtractor<V> extractor;
  
  public ColumnConfig(ColumnSpec spec, ColumnValueExtractor<V> extractor) {
    this.spec = spec;
    this.extractor = extractor;
  }
  
  public ColumnSpec getSpec() {
    return spec;
  }

  public ColumnValueExtractor<V> getExtractor() {
    return extractor;
  }
  
}
