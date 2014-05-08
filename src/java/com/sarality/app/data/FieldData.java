package com.sarality.app.data;

public interface FieldData<T> {

  public enum Type {
    BOOLEAN,
    DATE,
    DOUBLE,
    ENUM,
    INT,
    LONG,
    STRING,
    COMPLEX,  
  }

  public Field getField();
  
  public Type getType();

  public boolean hasValue();
  
  public T getValue();

  public void setValue(T value);

  public void castFrom(Object value);

  public void parseFrom(String stringValue);  
}
