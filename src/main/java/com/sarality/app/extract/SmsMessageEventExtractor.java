package com.sarality.app.extract;

import com.sarality.app.datastore.sms.SmsMessage;

import java.util.Set;

/**
 * Base class for all Extractors that extract events from an SMS Message.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class SmsMessageEventExtractor<E> implements MessageEventExtractor<SmsMessage, E> {

  private final Set<String> senderIds;

  public SmsMessageEventExtractor(Set<String> senderIdSet) {
    this.senderIds = senderIdSet;
  }

  protected Set<String> getSenderIds() {
    return senderIds;
  }
}
