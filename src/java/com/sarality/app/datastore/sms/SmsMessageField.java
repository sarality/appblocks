package com.sarality.app.datastore.sms;

import com.sarality.app.data.Field;

public enum SmsMessageField implements Field {
  MESSAGE_ID,
  ADDRESS,
  BODY;

  @Override
  public String getName() {
    return name();
  }
}
