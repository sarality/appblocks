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
    String sender = message.getAddress();
    return eventExtractorMap.get(sender == null ? null : sender.toUpperCase());
  }

  protected abstract void registerAllExtractors();

  /**
   * Register an extractor with the Provider.
   *
   * @param extractor Extractpr to be used for SMS Messages
   */
  protected final void register(SmsMessageEventExtractor<?> extractor) {
    Set<String> senderSet = extractor.getSenders();
    if (senderSet != null) {
      for (String sender : senderSet) {
        if (!eventExtractorMap.containsKey(sender)) {
          eventExtractorMap.put(sender, Lists.<SmsMessageEventExtractor<?>>emptyList());
        }
        eventExtractorMap.get(sender).add(extractor);
      }
    }
  }
}
