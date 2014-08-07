package com.sarality.app.datastore;

import java.util.Arrays;

import com.sarality.app.data.field.Field;
import com.sarality.app.datastore.column.ColumnProcessor;

/**
 * Defines a Mapping between a DataObject field and a Column in a DataStore, along with a processor to extract and store
 * date from the column, to and from the field.
 * <p>
 * The Mappings allows for a generic mechanism to load and update data.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class FieldColumnMapping {
  private final Field field;
  private final Column column;
  private final ColumnProcessor<?> processor;

  public FieldColumnMapping(Field field, Column column, ColumnProcessor<?> processor) {
    this.field = field;
    this.column = column;
    this.processor = processor;
    if (field == null) {
      throw new IllegalArgumentException("Cannot define mapping when the field is null");
    }
    if (column == null) {
      throw new IllegalArgumentException("Column mapped to field " + field.getName() + " cannot be null");
    }
    if (processor == null) {
      throw new IllegalArgumentException("Must define a processor for field " + field.getName() + " and column "
          + column.getName());
    }
  }

  /**
   * @return Field that the column is mapped to.
   */
  public final Field getField() {
    return field;
  }

  /**
   * @return DataStore column that this is a mapping for.
   */
  public final Column getColumn() {
    return column;
  }

  /**
   * @return Processor used to get data into and from a Column.
   */
  public final ColumnProcessor<?> getColumnProcessor() {
    return processor;
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(new Object[] { field, column, processor });
  }

  @Override
  public boolean equals(Object value) {
    if (value instanceof FieldColumnMapping) {
      FieldColumnMapping mapping = (FieldColumnMapping) value;
      return Arrays.equals(new Object[] { mapping.field, mapping.column, mapping.processor }, 
          new Object[] { field, column, processor });
    }
    return false;
  }
}
