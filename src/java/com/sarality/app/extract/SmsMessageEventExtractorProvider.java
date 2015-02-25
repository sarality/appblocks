package com.sarality.app.extract;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.common.collect.Maps;
import com.sarality.app.datastore.sms.SmsMessage;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Provides Extractors that should be used to get events from an SMS Message.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class SmsMessageEventExtractorProvider {

  private final Map<String, List<SmsMessageEventExtractor<?>>> eventExtractorMap = Maps.empty();

  public SmsMessageEventExtractorProvider() {
    super();
    registerAllExtractors();
  }

  public final List<SmsMessageEventExtractor<?>> provide(SmsMessage message) {
    String senderId = getSenderId(message.getAddress());
    return eventExtractorMap.get(senderId.toUpperCase());
  }

  protected abstract void registerAllExtractors();

  /**
   * Register an extractor with the Provider.
   *
   * @param extractor Extractor to be used for SMS Messages
   */
  protected final void register(SmsMessageEventExtractor<?> extractor) {
    Set<String> senderSet = extractor.getSenderIds();
    if (senderSet != null) {
      for (String sender : senderSet) {
        if (!eventExtractorMap.containsKey(sender)) {
          eventExtractorMap.put(sender, Lists.<SmsMessageEventExtractor<?>>emptyList());
        }
        eventExtractorMap.get(sender).add(extractor);
      }
    }
  }

  /**
   * Retrieve the Extractor lookup key (SenderId) for the SMS messages address.
   * <p/>
   * The lookup is used for commercial message where the address follows a specific format e.g. AD-CLRTRIP or
   * TM-MMTRIP, where the first two chars represent the Carrier and the City and the remaining address represents the
   * Sender Id.
   */
  private String getSenderId(String address) {
    if (address == null) {
      return null;
    }
    if (address.length() < 4) {
      return address.toUpperCase();
    }
    return address.substring(3).toUpperCase();
  }
}
