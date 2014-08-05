package com.sarality.app.datastore.column.test;

import com.sarality.app.data.DataObject.Builder;
import com.sarality.app.data.field.Field;
import com.sarality.app.data.field.FieldValueFactory;
import com.sarality.app.data.field.FieldValues;

/**
 * Test implementation of FieldValues used to create FieldValue objects for testing purposes.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class TestFieldValues extends FieldValues<Void> {

  protected TestFieldValues() {
    super(TestField.values(), null);
  }

  @Override
  public void registerFieldValueFactory(Field field, FieldValueFactory<?> factory) {
    super.registerFieldValueFactory(field, factory);
  }

  @Override
  public void populateFieldValues(Void data) {
    // Do nothing
  }

  @Override
  public Builder<Void> createBuilder() {
    return null;
  }
}
