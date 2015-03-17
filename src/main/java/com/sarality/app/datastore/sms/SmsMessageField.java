package com.sarality.app.datastore.sms;

import com.sarality.app.data.field.Field;

/**
 * Fields for {@link SmsMessage}.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public enum SmsMessageField implements Field {
  MESSAGE_ID(DataType.LONG),
  THREAD_ID(DataType.LONG),
  ADDRESS(DataType.STRING),
  BODY(DataType.STRING),
  MESSAGE_TYPE(DataType.ENUM),
  IS_READ(DataType.BOOLEAN),
  SENT_DATE(DataType.DATE),
  RECEIVED_DATE(DataType.DATE),
  RECIPIENT(DataType.STRING);

  private DataType dataType;

  private SmsMessageField(DataType dataType) {
    this.dataType = dataType;
  }

  @Override
  public String getName() {
    return name();
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }
}
