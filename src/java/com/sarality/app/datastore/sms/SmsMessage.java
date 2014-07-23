package com.sarality.app.datastore.sms;

import java.util.Date;

import com.sarality.app.data.BaseFieldBasedDataObject;
import com.sarality.app.data.BooleanFieldData;
import com.sarality.app.data.DataObject;
import com.sarality.app.data.DateFieldData;
import com.sarality.app.data.EnumFieldData;
import com.sarality.app.data.FieldBasedDataObject;
import com.sarality.app.data.LongFieldData;
import com.sarality.app.data.StringFieldData;
import com.sarality.app.datastore.MappedEnum;

/**
 * Class that represents an SMS message.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class SmsMessage extends BaseFieldBasedDataObject<SmsMessage> implements DataObject<SmsMessage> {

  // Id of the SMS message
  private final LongFieldData messageId = new LongFieldData(SmsMessageField.MESSAGE_ID, this);
  // Conversation Id
  private final LongFieldData threadId = new LongFieldData(SmsMessageField.THREAD_ID, this);
  
  // The address of the person/entity who this message is being 
  // exchanged with.
  private final StringFieldData address = new StringFieldData(SmsMessageField.ADDRESS, this);
  // Text of the SMS message
  private final StringFieldData body = new StringFieldData(SmsMessageField.BODY, this);

  // The folder the message is in (Sent, Inbox, Draft)
  private final EnumFieldData<MessageType> messageType = new EnumFieldData<MessageType>(SmsMessageField.MESSAGE_TYPE,
          MessageType.class, this);
  // Indicates whether the message has been read
  private final BooleanFieldData isRead = new BooleanFieldData(SmsMessageField.IS_READ, this);

  // Date and time the message was sent
  private final DateFieldData sentDate = new DateFieldData(SmsMessageField.SENT_DATE, this);
  // Date and time the message was received
  private final DateFieldData receivedDate = new DateFieldData(SmsMessageField.RECEIVED_DATE, this);

  // The Contact Id of the sender, if the sender is a contact.
  private final StringFieldData recipient = new StringFieldData(SmsMessageField.RECIPIENT, this);

  // Generated only via the Builder
  private SmsMessage() {
    super();
  }

  public final Long getMessageId() {
    return messageId.getValue();
  }

  public final String getBody() {
    return body.getValue();
  }
  
  public final Date getSentDate() {
    return sentDate.getValue();
  }
  
  public final Date getReceivedDate() {
    return receivedDate.getValue();
  }

  public String getAddress() {
    return address.getValue();
  }
  
  public String getRecipient() {
    return recipient.getValue();
  }

  public boolean isRead() {
    return isRead.getValue();
  }
 
  public MessageType getMessageType() {
    return messageType.getValue();
  }
  
  @Override
  public Builder getBuilder() {
    return new Builder()
        .setMessageId(messageId.getValue())
        .setThreadId(threadId.getValue())
        .setBody(body.getValue())
        .setAddress(address.getValue())
        .setRecipient(recipient.getValue())
        .setSentDate(sentDate.getValue())
        .setReceivedDate(receivedDate.getValue())
        .setRead(isRead.getValue())
        .setMessageType(messageType.getValue());
  }

  @Override
  public Builder newBuilder() {
    return new Builder();
  }

  @Override
  public String toString() {
    return new StringBuilder()
      .append("Message Id : ").append(messageId.getValue()).append(",\n")
      .append("Thread Id : ").append(threadId.getValue()).append(",\n")
      .append("Address : ").append(address.getValue()).append(",\n")
      .append("Recipient : ").append(recipient.getValue()).append(",\n")
      .append("Body : ").append(body.getValue()).append(",\n")
      .append("Sent : ").append(sentDate.getValue()).append(",\n")
      .append("Received : ").append(receivedDate.getValue()).append(",\n")
      .append("Read : ").append(isRead.getValue()).append(",\n")
      .append("Folder : ").append(messageType.getValue()).append("\n")
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

  public static class Builder extends BaseFieldBasedDataObject.Builder<SmsMessage> 
      implements FieldBasedDataObject.Builder<SmsMessage>, DataObject.Builder<SmsMessage> {

    @Override
    public SmsMessage newDataObject() {
      return new SmsMessage();
    }
    
    public Builder setMessageId(Long messageId) {
      data.messageId.setValue(messageId);
      return this;
    }

    public Builder setThreadId(Long threadId) {
      data.threadId.setValue(threadId);
      return this;
    }

    public Builder setBody(String body) {
      data.body.setValue(body);
      return this;
    }

    public Builder setAddress(String address) {
      data.address.setValue(address);
      return this;
    }    

    public Builder setRecipient(String recipient) {
      data.recipient.setValue(recipient);
      return this;
    }    

    public Builder setSentDate(Date sentDate) {
      data.sentDate.setValue(sentDate);
      return this;
    }    

    public Builder setReceivedDate(Date receivedDate) {
      data.receivedDate.setValue(receivedDate);
      return this;
    }

    public Builder setRead(Boolean isRead) {
      data.isRead.setValue(isRead);
      return this;
    }

    public Builder setMessageType(MessageType messageType) {
      data.messageType.setValue(messageType);
      return this;
    }
  }
}
