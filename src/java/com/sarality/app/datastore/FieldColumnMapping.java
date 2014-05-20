package com.sarality.app.datastore;

import com.sarality.app.data.Field;
import com.sarality.app.datastore.extractor.ColumnValueExtractor;
import com.sarality.app.datastore.populator.FieldDataValuePopulator;

public class FieldColumnMapping {
  private final Field field;
  private final Column column;
  private final ColumnValueExtractor<?> extractor;
  private final FieldDataValuePopulator populator;

  public FieldColumnMapping(Field field, Column column, ColumnValueExtractor<?> extractor, FieldDataValuePopulator populator) {
    super();
    this.field = field;
    this.column = column;
    this.extractor = extractor;
    this.populator = populator;
  }

  public Field getField() {
    return field;
  }

  public Column getColumn() {
    return column;
  }

  public ColumnValueExtractor<?> getExtractor() {
    return extractor;
  }

  public FieldDataValuePopulator getPopulator() {
    return populator;
  }
}
