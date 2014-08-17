package com.sarality.app.datastore.db.test;

import com.sarality.app.data.BaseFieldBasedDataObject;

public class TestObject extends BaseFieldBasedDataObject<TestObject> {
  Integer Id;
  String value;

  public final Builder newBuilder() {
    return new Builder();
  }

  public static class Builder extends BaseFieldBasedDataObject.Builder<TestObject> {

    public final Builder setId(Integer id) {
      data.Id = id;
      return this;
    }

    public final Builder setText(String value) {
      data.value = value;
      return this;
    }

    @Override
    protected TestObject newDataObject() {
      return new TestObject();
    }
  }

}