package com.sarality.app.datastore.test;

import com.sarality.app.data.DataObject;

public class TestObject implements DataObject<TestObject> {
  @Override
  public com.sarality.app.data.DataObject.Builder<TestObject> getBuilder() {
    return null;
  }
  @Override
  public com.sarality.app.data.DataObject.Builder<TestObject> newBuilder() {
    return null;
  }
}