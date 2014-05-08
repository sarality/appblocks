package com.sarality.app.datastore.sms;

import java.util.Arrays;
import java.util.Date;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.sarality.app.datastore.AbstractContentResolverDataStore;
import com.sarality.app.datastore.ColumnConfig;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnFormat;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.Query;
import com.sarality.app.datastore.extractor.BaseCursorDataExtractor;
import com.sarality.app.datastore.extractor.BooleanValueExtractor;
import com.sarality.app.datastore.extractor.ColumnValueExtractor;
import com.sarality.app.datastore.extractor.DateValueExtractor;
import com.sarality.app.datastore.extractor.LongValueExtractor;
import com.sarality.app.datastore.extractor.MappedEnumValueExtractor;
import com.sarality.app.datastore.extractor.StringValueExtractor;

public class SmsDataStore extends AbstractContentResolverDataStore<SmsMessage> {

  private static final String TAG = "SmsDataStore";

  private final Uri baseUri = Uri.parse("content://sms/");

  public SmsDataStore(Context context) {
    super(context.getApplicationContext(), 
          Arrays.<com.sarality.app.datastore.Column>asList(Column.values()),
          new SmsMessageExtractor());
  }

  public enum Column implements com.sarality.app.datastore.Column {
    _ID(new ColumnConfig<Long>(new ColumnSpec(ColumnDataType.INTEGER, false, null, null), new LongValueExtractor())),
    THREAD_ID(new ColumnConfig<Long>(new ColumnSpec(ColumnDataType.INTEGER, false), new LongValueExtractor())),
    ADDRESS(new ColumnConfig<String>(new ColumnSpec(ColumnDataType.TEXT, false), new StringValueExtractor())),
    PERSON(new ColumnConfig<String>(new ColumnSpec(ColumnDataType.TEXT, false), new StringValueExtractor())),
    BODY(new ColumnConfig<String>(new ColumnSpec(ColumnDataType.TEXT, false), new StringValueExtractor())),
    DATE(new ColumnConfig<Date>(new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.EPOCH, false), new DateValueExtractor())),
    DATE_SENT(new ColumnConfig<Date>(new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.EPOCH, false), new DateValueExtractor())),
    READ(new ColumnConfig<Boolean>(new ColumnSpec(ColumnDataType.BOOLEAN, false), new BooleanValueExtractor())),
    STATUS(new ColumnConfig<String>(new ColumnSpec(ColumnDataType.TEXT, false), new StringValueExtractor())),
    TYPE(new ColumnConfig<SmsMessage.MessageType>(new ColumnSpec(ColumnDataType.INTEGER, false),
            new MappedEnumValueExtractor<Integer, SmsMessage.MessageType>(SmsMessage.MessageType.values())));

    private ColumnConfig<?> config;

    private Column(ColumnConfig<?> config) {
      this.config = config;
    }
    
    @Override
    public String getName() {
      return name();
    }

    @Override
    public ColumnConfig<?> getConfig() {
      return config;
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

    public SmsMessage.MessageType getMessageType(Cursor cursor, Query query, Column column) {
      ColumnValueExtractor<?> extractor = column.getConfig().getExtractor();
      SmsMessage.MessageType value = (SmsMessage.MessageType) extractor.extract(cursor, column);
      return value;
    }
    
    @Override
    public SmsMessage extract(Cursor cursor, Query query) {
      SmsMessage.Builder builder = new SmsMessage.Builder();
      // TODO(abhideep): Use builder.setValue instead of explicit calls to methods

      if (hasColumn(cursor, query, Column._ID)) {
        builder.setMessageId(getLong(cursor, query, Column._ID));
      }
      if (hasColumn(cursor, query, Column.THREAD_ID)) {
        builder.setThreadId(getLong(cursor, query, Column.THREAD_ID));
      }
      if (hasColumn(cursor, query, Column.BODY)) {
        builder.setBody(getString(cursor, query, Column.BODY));
      }
      if (hasColumn(cursor, query, Column.ADDRESS)) {
        builder.setAddress(getString(cursor, query, Column.ADDRESS));
      }
      if (hasColumn(cursor, query, Column.DATE)) {
        builder.setReceivedDate(getDate(cursor, query, Column.DATE));
      }
      if (hasColumn(cursor, query, Column.DATE_SENT)) {
        builder.setSentDate(getDate(cursor, query, Column.DATE_SENT));
      }
      if (hasColumn(cursor, query, Column.READ)) {
        builder.setRead(getBoolean(cursor, query, Column.READ));
      }
      if (hasColumn(cursor, query, Column.TYPE)) {
        builder.setMessageType(getMessageType(cursor, query, Column.TYPE));
      }
      return builder.build();
    }    
  }
}
