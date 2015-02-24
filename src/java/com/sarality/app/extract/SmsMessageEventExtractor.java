package com.sarality.app.extract;

import com.sarality.app.datastore.sms.SmsMessage;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Base class for all Extractors that extract events from an SMS Message.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class SmsMessageEventExtractor<E> implements MessageEventExtractor<SmsMessage, E> {

  private final Set<String> senderIds;
  private final Pattern messagePattern;

  public SmsMessageEventExtractor(Set<String> senderIdSet, String messagePattern) {
    this.senderIds = senderIdSet;
    this.messagePattern = Pattern.compile(messagePattern);
  }

  protected Set<String> getSenderIds() {
    return senderIds;
  }

  protected Matcher createMatcher(SmsMessage message) {
    return messagePattern.matcher(message.getBody());
  }

  protected boolean matchesPattern(Matcher matcher) {
    return matcher.lookingAt();
  }
}
