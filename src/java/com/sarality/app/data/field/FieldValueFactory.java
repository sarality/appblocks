package com.sarality.app.data.field;

/**
 * Interface for all Factory classes that create a FieldValue.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public interface FieldValueFactory {

  /**
   * Create an instance of a FieldValue for the given Field with no data in it.
   * 
   * @param field Field to create the FieldValue for.
   * @return Empty FieldValue for the given Field.
   */
  public FieldValue<?> createFieldValue(Field field);
}
