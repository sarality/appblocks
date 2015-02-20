package com.sarality.app.extract;

import com.sarality.app.datastore.DataStore;
import com.sarality.app.datastore.DataStoreRegistry;
import com.sarality.app.datastore.query.Query;
import com.sarality.app.datastore.sms.SmsDataStore;
import com.sarality.app.datastore.sms.SmsMessage;

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

  private final DataStoreRegistry dataStoreRegistry;
  private final SmsMessageEventExtractorProvider provider;

  public SmsMessageDataProducer(SmsMessageEventExtractorProvider provider) {
    this(provider, DataStoreRegistry.getGlobalInstance());
  }

  public SmsMessageDataProducer(SmsMessageEventExtractorProvider provider, DataStoreRegistry dataStoreRegistry) {
    this.provider = provider;
    this.dataStoreRegistry = dataStoreRegistry;
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

  public void produce(SmsMessage message) {
    List<SmsMessageEventExtractor<?>> extractorList = provider.provide(message);
    if (extractorList != null) {
      for (SmsMessageEventExtractor<?> extractor : extractorList) {
        extractor.extract(message);
      }
    }
  }

}
