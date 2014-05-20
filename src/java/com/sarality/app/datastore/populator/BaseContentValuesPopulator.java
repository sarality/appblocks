package com.sarality.app.datastore.populator;



public abstract class BaseContentValuesPopulator<T> implements ContentValuesPopulator<T> {

  protected final LongValuePopulator longPopulator = new LongValuePopulator();
  protected final StringValuePopulator stringPopulator = new StringValuePopulator();
  protected final EnumValuePopulator enumPopulator = new EnumValuePopulator();
  protected final DateValuePopulator datePopulator = new DateValuePopulator();

  public LongValuePopulator getLongValuePopulator() {
    return longPopulator;
  }

  public StringValuePopulator getStringValuePopulator() {
    return stringPopulator;
  }

  public EnumValuePopulator getEnumValuePopulator() {
    return enumPopulator;
  }

  public DateValuePopulator getDateValuePopulator() {
    return datePopulator;
  }
}
