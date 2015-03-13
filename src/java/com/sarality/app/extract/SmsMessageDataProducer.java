package com.sarality.app.extract;

import com.sarality.app.datastore.DataStore;
import com.sarality.app.datastore.DataStoreRegistry;
import com.sarality.app.datastore.query.Query;
import com.sarality.app.datastore.sms.SmsDataStore;
import com.sarality.app.datastore.sms.SmsMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Produces data for an application based on the SMS messages on a device.
 * <p/>
 * The producer simply takes in a List of Extractors defined in the System, extracts events based on these extractors,
 * and then process the events to produce the data needed by the app.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class SmsMessageDataProducer {

  private static final Logger logger = LoggerFactory.getLogger(SmsMessageDataProducer.class);

  private final DataStoreRegistry dataStoreRegistry;
  private final SmsMessageEventExtractorProvider provider;
  private MessageEventProcessor processor;

  public SmsMessageDataProducer(SmsMessageEventExtractorProvider provider) {
    this(provider, DataStoreRegistry.getGlobalInstance());
  }

  public SmsMessageDataProducer(SmsMessageEventExtractorProvider provider, DataStoreRegistry dataStoreRegistry) {
    this.provider = provider;
    this.dataStoreRegistry = dataStoreRegistry;
  }

  public void setMessageEventProcessor(MessageEventProcessor processor) {
    this.processor = processor;
  }

  public void produce(Query smsDataStoreQuery) {
    @SuppressWarnings("unchecked")
    DataStore<SmsMessage> dataStore =
        (DataStore<SmsMessage>) dataStoreRegistry.getDataStore(SmsDataStore.DATASTORE_NAME);
    List<SmsMessage> messageList = dataStore.query(smsDataStoreQuery);

    for (SmsMessage message : messageList) {
      produce(message);
    }
  }

  @SuppressWarnings("unchecked")
  public void produce(SmsMessage message) {
    List<SmsMessageEventExtractor<?>> extractorList = provider.provide(message);
    if (extractorList != null) {
      for (SmsMessageEventExtractor<?> extractor : extractorList) {
        try {
          MessageEventList<SmsMessage, ?> messageEvents = extractor.extract(message);
          if (messageEvents != null && processor != null) {
            try {
              processor.processEvents(messageEvents);
            } catch (Throwable t) {
              logger.error("Error processing events produced by message from " + message.getAddress(), t);
            }
          }
        } catch (Throwable t) {
          logger.error("Error extracting events from message sent by " + message.getAddress(), t);
        }
      }
    }
  }
}
