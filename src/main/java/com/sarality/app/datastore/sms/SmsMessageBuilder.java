package com.sarality.app.datastore.sms;

import java.util.Date;

import com.sarality.app.data.DataObject;
import com.sarality.app.datastore.sms.SmsMessage.MessageType;

/**
 * Builder for {@link SmsMessage}.
 * 
 * @author abhideep@ (abhideep Singh)
 */
public class SmsMessageBuilder implements DataObject.Builder<SmsMessage> {

  // Id of the SMS message
  private Long messageId;
  // Conversation Id
  private Long threadId;

  // The address of the person/entity who this message is being
  // exchanged with.
  private String address;
  // Text of the SMS message
  private String body;

  // The folder the message is in (Sent, Inbox, Draft)
  private MessageType messageType;

  // Indicates whether the message has been read
  private Boolean isRead;

  // Date and time the message was sent
  private Date sentDate;
  // Date and time the message was received
  private Date receivedDate;

  // The Contact Id of the sender, if the sender is a contact.
  private String recipient;

  @Override
  public SmsMessage build() {
    return new SmsMessage(messageId, threadId, address, body, messageType, isRead, receivedDate, sentDate, recipient);
  }

  public SmsMessageBuilder setMessageId(Long messageId) {
    this.messageId = messageId;
    return this;
  }

  public SmsMessageBuilder setThreadId(Long threadId) {
    this.threadId = threadId;
    return this;
  }

  public SmsMessageBuilder setAddress(String address) {
    this.address = address;
    return this;
  }

  public SmsMessageBuilder setBody(String body) {
    this.body = body;
    return this;
  }

  public SmsMessageBuilder setMessageType(MessageType messageType) {
    this.messageType = messageType;
    return this;
  }

  public SmsMessageBuilder setRead(Boolean isRead) {
    this.isRead = isRead;
    return this;
  }

  public SmsMessageBuilder setSentDate(Date sentDate) {
    this.sentDate = sentDate;
    return this;
  }

  public SmsMessageBuilder setReceivedDate(Date receivedDate) {
    this.receivedDate = receivedDate;
    return this;
  }

  public SmsMessageBuilder setRecipient(String recipient) {
    this.recipient = recipient;
    return this;
  }

}
