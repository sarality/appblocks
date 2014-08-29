package com.sarality.app.datastore.sms;

import java.util.Date;

import com.sarality.app.data.DataObject;
import com.sarality.app.datastore.MappedEnum;

/**
 * Class that represents an SMS message.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class SmsMessage implements DataObject<SmsMessage> {

  // Id of the SMS message
  private final Long messageId;

  // Conversation Id
  private final Long threadId;
  
  // The address of the person/entity who this message is being 
  // exchanged with.
  private final String address;
  // Text of the SMS message
  private final String body;

  // The folder the message is in (Sent, Inbox, Draft)
  private final MessageType messageType;

  // Indicates whether the message has been read
  private final Boolean isRead;

  // Date and time the message was sent
  private final Date sentDate;
  // Date and time the message was received
  private final Date receivedDate;

  // The Contact Id of the sender, if the sender is a contact.
  private final String recipient;

  public SmsMessage(Long messageId, Long threadId, String address, String body, MessageType messageType,
      Boolean isRead, Date sentDate, Date receivedDate, String recipient) {
    super();
    this.messageId = messageId;
    this.threadId = threadId;
    this.address = address;
    this.body = body;
    this.messageType = messageType;
    this.isRead = isRead;
    this.sentDate = sentDate;
    this.receivedDate = receivedDate;
    this.recipient = recipient;
  }
  
  public final Long getMessageId() {
    return messageId;
  }

  public final String getBody() {
    return body;
  }
  
  public final Date getSentDate() {
    return sentDate;
  }
  
  public final Date getReceivedDate() {
    return receivedDate;
  }

  public String getAddress() {
    return address;
  }
  
  public String getRecipient() {
    return recipient;
  }

  public boolean isRead() {
    return isRead;
  }
 
  public MessageType getMessageType() {
    return messageType;
  }
  
  @Override
  public SmsMessageBuilder getBuilder() {
    return new SmsMessageBuilder()
        .setMessageId(messageId)
        .setThreadId(threadId)
        .setBody(body)
        .setAddress(address)
        .setRecipient(recipient)
        .setSentDate(sentDate)
        .setReceivedDate(receivedDate)
        .setRead(isRead)
        .setMessageType(messageType);
  }

  @Override
  public SmsMessageBuilder newBuilder() {
    return new SmsMessageBuilder();
  }

  @Override
  public String toString() {
    return new StringBuilder()
      .append("Message Id : ").append(messageId).append(",\n")
      .append("Thread Id : ").append(threadId).append(",\n")
      .append("Address : ").append(address).append(",\n")
      .append("Recipient : ").append(recipient).append(",\n")
      .append("Body : ").append(body).append(",\n")
      .append("Sent : ").append(sentDate).append(",\n")
      .append("Received : ").append(receivedDate).append(",\n")
      .append("Read : ").append(isRead).append(",\n")
      .append("Folder : ").append(messageType).append("\n")
      .toString();
  }

  public static enum MessageType implements MappedEnum<Integer> {
    RECEIVED(1),
    SENT(2),
    DRAFT(3);

    private int mappedValue;

    private MessageType(int mappedValue) {
      this.mappedValue = mappedValue;
    }

    @Override
    public Integer getMappedValue() {
      return mappedValue;
    }
  }
}
