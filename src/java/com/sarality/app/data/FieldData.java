package com.sarality.app.data;

public interface FieldData<T> {

  public enum Type {
    STRING,
    LONG,
    INT,
    DATE,
    ENUM,
    COMPLEX, 
  }

  public Field getField();
  
  public Type getType();

  public boolean hasValue();
  
  public T getValue();

  public void setValue(T value);
  
  public void parseFrom(String stringValue);
}
