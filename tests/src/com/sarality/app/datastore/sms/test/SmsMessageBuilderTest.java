package com.sarality.app.datastore.sms.test;

import java.util.Date;

import junit.framework.TestCase;

import com.sarality.app.datastore.sms.SmsMessage;
import com.sarality.app.datastore.sms.SmsMessageBuilder;

/**
 * Tests for SmsMessageBuilder.
 * <p>
 * TODO(abhideep): This is a simple port. Add some proper Unit tests here.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class SmsMessageBuilderTest extends TestCase {

  private final String emptyString = new StringBuilder().append("Message Id : null,\n").append("Thread Id : null,\n")
      .append("Address : null,\n").append("Recipient : null,\n").append("Body : null,\n").append("Sent : null,\n")
      .append("Received : null,\n").append("Read : null,\n").append("Folder : null\n").toString();

  public void testSmsMessage_EmptyBuilder() {
    SmsMessageBuilder builder = new SmsMessageBuilder();
    SmsMessage smsMessage = builder.build();

    assertEquals(emptyString, smsMessage.toString());
  }

  public void testGetBuilder() {
    SmsMessageBuilder builder = new SmsMessageBuilder();
    SmsMessage smsMessage = builder.setMessageId(Long.valueOf("1234567890")).setAddress("Test Box")
        .setBody("Testing....").setRecipient("Jane Doe").setReceivedDate(new Date()).setRead(false).build();

    assertNotNull(smsMessage.getBuilder());
    assertEquals(Long.valueOf("1234567890"), smsMessage.getMessageId());
    assertEquals("Testing....", smsMessage.getBody());
    assertEquals("Jane Doe", smsMessage.getRecipient());
    assertEquals(false, smsMessage.isRead());
  }

  public void testGetBuilder_EmptyMessage() {
    SmsMessageBuilder builder = new SmsMessageBuilder();
    SmsMessage smsMessage = builder.build();

    assertNotNull(smsMessage.getBuilder());
    assertEquals(smsMessage.getBuilder().build().toString(), smsMessage.toString());
  }

  public void testNewBuilder() {
    SmsMessageBuilder builder = new SmsMessageBuilder();
    assertNotNull(builder);
  }
}
