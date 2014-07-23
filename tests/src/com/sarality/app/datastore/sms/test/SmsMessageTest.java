package com.sarality.app.datastore.sms.test;

import java.util.Date;

import junit.framework.TestCase;

import com.sarality.app.datastore.sms.SmsMessage;
import com.sarality.app.datastore.sms.SmsMessage.MessageType;

/**
 * Tests for {@link SmsMessageTest}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class SmsMessageTest extends TestCase {

  private final String emptyString = new StringBuilder().append("Message Id : null,\n").append("Thread Id : null,\n")
      .append("Address : null,\n").append("Recipient : null,\n").append("Body : null,\n").append("Sent : null,\n")
      .append("Received : null,\n").append("Read : null,\n").append("Folder : null\n").toString();

  public void testToString() {
    SmsMessage.Builder builder = new SmsMessage.Builder();
    SmsMessage smsMessage = builder.newDataObject();
    assertNotNull(smsMessage.toString());
    assertEquals(emptyString, smsMessage.toString());
  }

  public void testSmsMessage() {
    Long longValue = Long.valueOf("1234567890123456789");
    Date now = new Date();

    SmsMessage message = new SmsMessage.Builder().setMessageId(longValue).setBody("testing").setSentDate(now)
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

  public void testSmsMessage_EmptyBuilder() {
    SmsMessage.Builder builder = new SmsMessage.Builder();
    SmsMessage smsMessage = builder.build();

    assertEquals(emptyString, smsMessage.toString());
  }

  public void testGetBuilder() {
    SmsMessage.Builder builder = new SmsMessage.Builder();
    SmsMessage smsMessage = builder.setMessageId(Long.valueOf("1234567890")).setAddress("Test Box")
        .setBody("Testing....").setRecipient("Jane Doe").setReceivedDate(new Date()).setRead(false).build();

    assertNotNull(smsMessage.getBuilder());
    assertEquals(Long.valueOf("1234567890"), smsMessage.getMessageId());
    assertEquals("Testing....", smsMessage.getBody());
    assertEquals("Jane Doe", smsMessage.getRecipient());
    assertEquals(false, smsMessage.isRead());
  }

  public void testGetBuilder_EmptyMessage() {
    SmsMessage.Builder builder = new SmsMessage.Builder();
    SmsMessage smsMessage = builder.build();

    assertNotNull(smsMessage.getBuilder());
    assertEquals(smsMessage.getBuilder().build().toString(), smsMessage.toString());
  }

  public void testNewBuilder() {
    SmsMessage.Builder builder = new SmsMessage.Builder();
    assertNotNull(builder);
  }
}
