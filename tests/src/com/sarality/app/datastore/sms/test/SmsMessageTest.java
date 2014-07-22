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

  public void testToString() {
    SmsMessage.Builder builder = new SmsMessage.Builder();
    SmsMessage smsMessage = builder.newDataObject();
    assertNotNull(smsMessage.toString());
  }

  public void testGetMessageId() {
    SmsMessage.Builder builder = new SmsMessage.Builder();
    Long longValue = Long.valueOf("1234567890123456789");
    SmsMessage message = builder.setMessageId(longValue).build();
    assertEquals(message.getMessageId(), longValue);
  }

  public void testGetBody() {
    SmsMessage.Builder builder = new SmsMessage.Builder();
    SmsMessage message = builder.setBody("testing").build();
    assertEquals(message.getBody(), "testing");
  }

  public void testGetSentDate() {
    SmsMessage.Builder builder = new SmsMessage.Builder();
    Date now = new Date();
    SmsMessage message = builder.setSentDate(now).build();
    assertEquals(message.getSentDate(), now);
  }

  public void testGetReceivedDate() {
    SmsMessage.Builder builder = new SmsMessage.Builder();
    Date now = new Date();
    SmsMessage message = builder.setReceivedDate(now).build();
    assertEquals(message.getReceivedDate(), now);
  }

  public void testGetAddress() {
    SmsMessage.Builder builder = new SmsMessage.Builder();
    SmsMessage message = builder.setAddress("testAddress").build();
    assertEquals(message.getAddress(), "testAddress");
  }

  public void testGetRecipient() {
    SmsMessage.Builder builder = new SmsMessage.Builder();
    SmsMessage message = builder.setRecipient("John Doe").build();
    assertEquals(message.getRecipient(), "John Doe");
  }

  public void testIsRead() {
    SmsMessage.Builder builder = new SmsMessage.Builder();
    SmsMessage message = builder.setRead(false).build();
    assertEquals(message.isRead(), false);
  }

  public void testGetMessageType() {
    SmsMessage.Builder builder = new SmsMessage.Builder();
    SmsMessage message = builder.setMessageType(MessageType.DRAFT).build();
    assertEquals(message.getMessageType(), MessageType.DRAFT);
  }

  public void testGetBuilder() {
    SmsMessage.Builder builder = new SmsMessage.Builder();
    SmsMessage smsMessage = builder.setMessageId(Long.valueOf("1234567890")).
        setAddress("Test Box").
        setBody("Testing....").
        setRecipient("Jane Doe").
        setReceivedDate(new Date()).
        setRead(false).build();

    assertNotNull(smsMessage.getBuilder());
  }

  public void testNewBuilder() {
    SmsMessage.Builder builder = new SmsMessage.Builder();
    assertNotNull(builder);
  }

}
