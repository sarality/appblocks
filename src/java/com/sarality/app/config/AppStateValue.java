package com.sarality.app.config;

public interface AppStateValue<T> {

  public T getValue();
  
  public boolean isEditable();

  public void setValue(T value);
}
