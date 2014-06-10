package com.sarality.app.data;

import java.util.Set;

/**
 * A {@link DataObject} that stores its data in {@link FieldData} objects
 * making it possible to set and get values of field in a generic manner.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> Type of DataObject
 */
public interface FieldBasedDataObject<T> extends DataObject<T> {

  /**
   * @return Set of all possible Fields in the DataObject.
   */
  public Set<Field> getFields();

  /**
   * Indicates whether DataObject has any data for the given field.
   * 
   * @param field The field to check the value for
   * @return {@code true} is the DataObject has a value for the field, {@code false} otherwise.
   */
  public boolean hasValue(Field field);

  /**
   * Retrieve the value of the field as an {@code Object} that can be cast appropriately.
   * 
   * @param field The field to retrieve value for.
   * @return Value for the field.
   */
  public Object getValue(Field field);

  /**
   * Retrieve the value of the field as {@code FieldData} that can be cast appropriately.
   * 
   * @param field The field to retrieve value for.
   * @return Value for the field.
   */
  public FieldData<?> getFieldData(Field field);
  
  @Override
  public Builder<T> getBuilder();

  @Override
  public Builder<T> newBuilder();  

  /**
   * Interface for all Builders for FieldBasedDataObjects.
   * 
   * @author abhideep@ (Abhideep Singh)
   *
   * @param <T> Type of data object returned by the Builder.
   */
  public interface Builder<T> extends DataObject.Builder<T> {

    /**
     * Set the value of the given field by casting it from the given Object value.
     * 
     * @param field The field whose value needs to be set.
     * @param value The value for the field.
     */
    public void setFieldValue(Field field, Object value);

    /**
     * Set the value of the given field by parsing it from the given String value.
     * 
     * @param field The field whose value needs to be set.
     * @param value The value for the field.
     */
    public void parseFieldValue(Field field, String value);

    @Override
    public T build();
  }

  /**
   * A Factory to create empty Builders for FieldBasedDataObjects.
   * 
   * @author abhideep@ (Abhideep Singh)
   *
   * @param <T> Type of data object returned by the builder.
   */
  public interface BuilderFactory<T> extends DataObject.BuilderFactory<T> {
 
    @Override
    public Builder<T> newBuilder();
  }
}
