package com.sarality.app.datastore.sms;

import java.util.Arrays;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.sarality.app.datastore.AbstractContentResolverDataStore;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnFormat;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.CursorDataExtractor;
import com.sarality.app.datastore.column.ColumnProcessor;
import com.sarality.app.datastore.column.ColumnProcessors;
import com.sarality.app.datastore.query.Query;
import com.sarality.app.datastore.sms.SmsMessage.MessageType;

/**
 * DataStore to store all the relevant SMSMessages
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class SmsDataStore extends AbstractContentResolverDataStore<SmsMessage> {

  public static final String DATASTORE_NAME = "SmsDataStore";
  private final Context context;

  private final Uri baseUri = Uri.parse("content://sms/");

  /**
   * Constructor
   * 
   * @param context : Application context
   */
  public SmsDataStore(Context context) {
    super(DATASTORE_NAME, Arrays.<com.sarality.app.datastore.Column> asList(Column.values()), new SmsMessageExtractor());
    this.context = context;
  }

  /**
   * Defines the list of columns specific to the SMSDataStore
   * 
   * @author abhideep@ (Abhideep Singh)
   */
  public enum Column implements com.sarality.app.datastore.Column {
    _ID(new ColumnSpec(ColumnDataType.INTEGER, false, null, null)),
    THREAD_ID(new ColumnSpec(ColumnDataType.INTEGER, false)),
    ADDRESS(new ColumnSpec(ColumnDataType.TEXT, false)),
    PERSON(new ColumnSpec(ColumnDataType.TEXT, false)),
    BODY(new ColumnSpec(ColumnDataType.TEXT, false)),
    DATE(new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.EPOCH, false)),
    // TODO(abhideep): Reintroduce after adding concept of optional or min/max API version in the spec
    // DATE_SENT(new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.EPOCH, false)),
    READ(new ColumnSpec(ColumnDataType.BOOLEAN, false)),
    STATUS(new ColumnSpec(ColumnDataType.TEXT, false)),
    TYPE(new ColumnSpec(ColumnDataType.INTEGER, false));

    private ColumnSpec spec;

    private Column(ColumnSpec spec) {
      this.spec = spec;
    }

    @Override
    public String getName() {
      return name();
    }

    @Override
    public ColumnSpec getSpec() {
      return spec;
    }
  }

  @Override
  public Uri getQueryUri(Query query) {
    return baseUri;
  }

  @Override
  public ContentResolver getContentResolver() {
    return context.getApplicationContext().getContentResolver();
  }

  public static class SmsMessageExtractor implements CursorDataExtractor<SmsMessage> {
    
    private final ColumnProcessors processors = new ColumnProcessors();
    private final ColumnProcessor<MessageType> messageTypeProcessor = processors.forMappedEnum(MessageType.class, 
        MessageType.values());

    @Override
    public SmsMessage extract(Cursor cursor, Query query) {
      SmsMessageBuilder builder = new SmsMessageBuilder();
      builder.setMessageId(processors.forLong().extract(cursor, Column._ID));
      builder.setThreadId(processors.forLong().extract(cursor, Column.THREAD_ID));
      builder.setAddress(processors.forString().extract(cursor, Column.ADDRESS));
      builder.setBody(processors.forString().extract(cursor, Column.BODY));
      builder.setMessageType(messageTypeProcessor.extract(cursor, Column.TYPE));
      builder.setRead(processors.forBoolean().extract(cursor, Column.READ));
      builder.setReceivedDate(processors.forDate().extract(cursor, Column.DATE));
      // TODO(abhideep): Reintroduce after adding concept of optional or min/max API version in the spec
      // builder.setSentDate(processors.forDate().extract(cursor, Column.DATE_SENT));
      builder.setRecipient(processors.forString().extract(cursor, Column.PERSON));
      return builder.build();
    }    
  }
}
