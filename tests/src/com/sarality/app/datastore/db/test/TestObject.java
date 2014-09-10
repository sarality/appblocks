package com.sarality.app.datastore.db.test;

import com.sarality.app.data.DataObject;

public class TestObject implements DataObject<TestObject> {
  private final Integer id;
  private final String value;

  public TestObject(Integer id, String value) {
    this.id = id;
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public final Builder newBuilder() {
    return new Builder();
  }

  @Override
  public Builder getBuilder() {
    return new Builder().setId(id).setText(value);
  }

  public static class Builder implements DataObject.Builder<TestObject> {
    private Integer id;
    private String value;

    public final Builder setId(Integer id) {
      this.id = id;
      return this;
    }

    public final Builder setText(String value) {
      this.value = value;
      return this;
    }

    @Override
    public TestObject build() {
      return new TestObject(id, value);
    }
  }
}
