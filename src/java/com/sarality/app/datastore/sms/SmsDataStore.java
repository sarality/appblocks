package com.sarality.app.datastore.sms;

import java.util.Arrays;
import java.util.Set;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.sarality.app.datastore.AbstractContentResolverDataStore;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.Query;
import com.sarality.app.datastore.extractor.BaseCursorDataExtractor;
import com.sarality.app.datastore.extractor.MappedEnumValueExtractor;

public class SmsDataStore extends AbstractContentResolverDataStore<SmsMessage> {

  private static final String TAG = "SmsDataStore";

  private final Uri baseUri = Uri.parse("content://sms/");

  public SmsDataStore(Context context) {
    super(context.getApplicationContext(), 
          Arrays.<com.sarality.app.datastore.Column>asList(Column.values()),
          new SmsMessageExtractor());
  }

  public enum Column implements com.sarality.app.datastore.Column {
    _ID(new ColumnSpec(Column.DataType.INTEGER, false)),
    THREAD_ID(new ColumnSpec(Column.DataType.INTEGER, false)),
    ADDRESS(new ColumnSpec(Column.DataType.TEXT, false)),
    PERSON(new ColumnSpec(Column.DataType.TEXT, false)),
    BODY(new ColumnSpec(Column.DataType.TEXT, false)),
    DATE(new ColumnSpec(Column.DataType.DATETIME, Column.DataTypeFormat.EPOCH, false)),
    DATE_SENT(new ColumnSpec(Column.DataType.DATETIME, Column.DataTypeFormat.EPOCH, false)),
    READ(new ColumnSpec(Column.DataType.BOOLEAN, false)),
    STATUS(new ColumnSpec(Column.DataType.TEXT, false)),
    TYPE(new ColumnSpec(Column.DataType.INTEGER, false));

    private ColumnSpec spec;

    private Column(ColumnSpec spec) {
      this.spec = spec;
    }
    
    @Override
    public String getName() {
      return name();
    }

    @Override
    public DataType getDataType() {
      return spec.getDataType();
    }

    @Override
    public DataTypeFormat getFormat() {
      return spec.getFormat();
    }

    @Override
    public int getSize() {
      return spec.getSize();
    }

    @Override
    public boolean isRequired() {
      return spec.isRequired();
    }

    @Override
    public Set<Property> getProperties() {
      return spec.getProperties();
    }
  }

  @Override
  public Uri getQueryUri(Query query) {
    return baseUri;
  }

  @Override
  public String getLoggerTag() {
    return TAG;
  }

  public static class SmsMessageExtractor extends BaseCursorDataExtractor<SmsMessage> {
    private MappedEnumValueExtractor<Integer, SmsMessage.MessageType> messageTypeExtractor = 
            new MappedEnumValueExtractor<Integer, SmsMessage.MessageType>(SmsMessage.MessageType.values());

    @Override
    public SmsMessage extract(Cursor cursor, Query query) {
      SmsMessage.Builder builder = new SmsMessage.Builder();
      if (hasColumn(cursor, query, Column._ID)) {
        builder.setMessageId(longExtractor.extract(cursor, Column._ID));
      }
      if (hasColumn(cursor, query, Column.THREAD_ID)) {
        builder.setThreadId(longExtractor.extract(cursor, Column.THREAD_ID));
      }
      if (hasColumn(cursor, query, Column.BODY)) {
        builder.setBody(stringExtractor.extract(cursor, Column.BODY));
      }
      if (hasColumn(cursor, query, Column.ADDRESS)) {
        builder.setAddress(stringExtractor.extract(cursor, Column.ADDRESS));
      }
      if (hasColumn(cursor, query, Column.DATE)) {
        builder.setReceivedDate(dateExtractor.extract(cursor, Column.DATE));
      }
      if (hasColumn(cursor, query, Column.DATE_SENT)) {
        builder.setSentDate(dateExtractor.extract(cursor, Column.DATE_SENT));
      }
      if (hasColumn(cursor, query, Column.READ)) {
        builder.setRead(booleanExtractor.extract(cursor, Column.READ));
      }
      if (hasColumn(cursor, query, Column.TYPE)) {
        builder.setMessageType(messageTypeExtractor.extract(cursor, Column.TYPE));
      }
      return builder.build();
    }    
  }
}
