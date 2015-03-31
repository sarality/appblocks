package com.sarality.app.extract;

import android.util.Log;

import com.dothat.app.module.flight.PdfData;
import com.dothat.app.module.flight.extract.ETicketFlightEventExtractorProvider;
import com.dothat.app.module.flight.extract.ETicketFlightExtractor;
import com.dothat.app.module.flight.extract.SmsMessageSenders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Produces data for an application based on the SMS messages on a device.
 * <p/>
 * The producer simply takes in a List of Extractors defined in the System, extracts events based on these extractors,
 * and then process the events to produce the data needed by the app.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ETicketDataProducer {

  private static final Logger logger = LoggerFactory.getLogger(ETicketDataProducer.class);
  ETicketFlightEventExtractorProvider provider;
  private MessageEventProcessor processor;

  public ETicketDataProducer(ETicketFlightEventExtractorProvider provider) {
    this.provider = provider;
  }

  public void produce() {
    List<ETicketFlightExtractor> extractorList = provider.provide(SmsMessageSenders.INDIGO_PDF_PARSER_ID);
    Map<String, String> pdfDataList = new PdfData().getAllPdfData();

    if (extractorList != null) {
      for (Map.Entry<String, String> entry : pdfDataList.entrySet()) {
        Log.d("ETicketDataProducer", "-----------******************************-----------");
        Log.d("ETicketDataProducer", "Parse PDF for String " + entry.getKey());
        for (ETicketFlightExtractor extractor : extractorList) {
          try {
            MessageEventList<String, ?> messageEvents = extractor.extract(entry.getValue());
            Log.d("ETicketDataProducer", "MessageEventList is " + messageEvents);
            if (messageEvents != null && processor != null) {
              try {
                processor.processEvents(messageEvents);
                break;
              } catch (Throwable t) {
                logger.error("Error processing events produced by message from " + t);
                t.printStackTrace();
              }
            }
          } catch (Throwable t) {
            logger.error("Error extracting events from message sent by " + t);
            t.printStackTrace();
          }
        }
      }
    }
  }

  public void setMessageEventProcessor(MessageEventProcessor processor) {
    this.processor = processor;
  }

}
