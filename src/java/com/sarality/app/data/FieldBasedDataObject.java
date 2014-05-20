package com.sarality.app.data;

import java.util.Set;


public interface FieldBasedDataObject<T> extends DataObject<T> {

  public Set<Field> getFields();

  public boolean hasValue(Field field);

  public Object getValue(Field field);

  public FieldData<?> getFieldData(Field field);
  
  @Override
  public Builder<T> getBuilder();

  @Override
  public Builder<T> newBuilder();  

  public interface Builder<T> extends DataObject.Builder<T> {

    public void setFieldValue(Field field, Object value);

    public void parseFieldValue(Field field, String value);

    @Override
    public T build();
  }
}
