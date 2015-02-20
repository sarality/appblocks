package com.sarality.app.extract;

import com.sarality.app.common.collect.Sets;
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

  private final Set<String> senders;
  private final Pattern messagePattern;

  public SmsMessageEventExtractor(Set<String> senders, String messagePattern) {
    this.senders = toUpperCase(senders);
    this.messagePattern = Pattern.compile(messagePattern);
  }

  public SmsMessageEventExtractor(String messagePattern) {
    this(Sets.<String>emptySet(), messagePattern);
  }

  protected Set<String> getSenders() {
    return senders;
  }

  protected Matcher createMatcher(SmsMessage message) {
    return messagePattern.matcher(message.getBody());
  }

  protected boolean matchesPattern(Matcher matcher) {
    return matcher.lookingAt();
  }

  private static Set<String> toUpperCase(Set<String> senders) {
    if (senders == null) {
      return null;
    }
    Set<String> upperCaseSender = Sets.emptySet();
    for (String sender : senders) {
      upperCaseSender.add(sender.toUpperCase());
    }
    return upperCaseSender;
  }
}
