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
  private boolean isRead;

  // Date and time the message was sent
  private Date sentDate;
  // Date and time the message was received
  private Date receivedDate;

  // The Contact Id of the sender, if the sender is a contact.
  private String recipient;
  
  private SmsMessage() {
    // Generated only via the Builder
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
  public Builder getBuilder() {
    return new Builder()
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
  public Builder newBuilder() {
    return new Builder();
  }

  @Override
  public String toString() {
    return new StringBuilder()
      .append("Message Id : ").append(messageId).append(",\n")
      .append("Thread Id : ").append(messageId).append(",\n")
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

  public static class Builder implements DataObject.Builder<SmsMessage> {
    private SmsMessage data = new SmsMessage();
    
    @Override
    public SmsMessage build() {
      return data;
    }
    
    public Builder setMessageId(Long messageId) {
      data.messageId = messageId;
      return this;
    }

    public Builder setThreadId(Long threadId) {
      data.threadId = threadId;
      return this;
    }

    public Builder setBody(String body) {
      data.body = body;
      return this;
    }

    public Builder setAddress(String address) {
      data.address = address;
      return this;
    }    

    public Builder setRecipient(String recipient) {
      data.recipient = recipient;
      return this;
    }    

    public Builder setSentDate(Date sentDate) {
      data.sentDate = sentDate;
      return this;
    }    

    public Builder setReceivedDate(Date receivedDate) {
      data.receivedDate = receivedDate;
      return this;
    }

    public Builder setRead(boolean isRead) {
      data.isRead = isRead;
      return this;
    }    

    public Builder setMessageType(MessageType messageType) {
      data.messageType = messageType;
      return this;
    }    
  }
}
