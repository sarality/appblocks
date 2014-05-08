package com.sarality.app.datastore.sms;

import com.sarality.app.data.Field;

public enum SmsMessageField implements Field {
  MESSAGE_ID,
  THREAD_ID,
  ADDRESS,
  BODY,
  MESSAGE_TYPE, IS_READ, SENT_DATE, RECEIVED_DATE, RECIPIENT,
  ;

  @Override
  public String getName() {
    return name();
  }
}
