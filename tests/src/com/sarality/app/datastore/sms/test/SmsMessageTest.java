package com.sarality.app.datastore.sms.test;

import java.util.Date;

import junit.framework.TestCase;

import com.sarality.app.datastore.sms.SmsMessage;
import com.sarality.app.datastore.sms.SmsMessage.MessageType;
import com.sarality.app.datastore.sms.SmsMessageBuilder;

/**
 * Tests for {@link SmsMessageTest}.
 * <p>
 * TODO(abhideep): This is a simple port. Add some proper Unit tests here.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class SmsMessageTest extends TestCase {

  private final String emptyString = new StringBuilder().append("Message Id : null,\n").append("Thread Id : null,\n")
      .append("Address : null,\n").append("Recipient : null,\n").append("Body : null,\n").append("Sent : null,\n")
      .append("Received : null,\n").append("Read : null,\n").append("Folder : null\n").toString();

  public void testToString() {
    SmsMessageBuilder builder = new SmsMessageBuilder();
    SmsMessage smsMessage = builder.build();
    assertNotNull(smsMessage.toString());
    assertEquals(emptyString, smsMessage.toString());
  }

  public void testSmsMessage() {
    Long longValue = Long.valueOf("1234567890123456789");
    Date now = new Date();

    SmsMessage message = new SmsMessageBuilder().setMessageId(longValue).setBody("testing").setSentDate(now)
        .setReceivedDate(now).setAddress("testAddress").setRecipient("John Doe").setRead(false)
        .setMessageType(MessageType.DRAFT).build();

    assertEquals(longValue, message.getMessageId());
    assertEquals("testing", message.getBody());
    assertEquals(now, message.getSentDate());
    assertEquals(now, message.getReceivedDate());
    assertEquals("testAddress", message.getAddress());
    assertEquals("John Doe", message.getRecipient());
    assertEquals(false, message.isRead());
    assertEquals(MessageType.DRAFT, message.getMessageType());
  }
}
