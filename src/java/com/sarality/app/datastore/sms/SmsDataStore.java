package com.sarality.app.datastore.sms;

import java.util.Arrays;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.sarality.app.datastore.AbstractContentResolverDataStore;
import com.sarality.app.datastore.ColumnConfig;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnFormat;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.Query;
import com.sarality.app.datastore.extractor.CursorDataExtractor;
import com.sarality.app.datastore.extractor.Extractors;
import com.sarality.app.datastore.sms.SmsMessage.MessageType;

public class SmsDataStore extends AbstractContentResolverDataStore<SmsMessage> {

  private static final String TAG = "SmsDataStore";

  private final Uri baseUri = Uri.parse("content://sms/");

  public SmsDataStore(Context context) {
    super(context.getApplicationContext(), 
          Arrays.<com.sarality.app.datastore.Column>asList(Column.values()),
          new SmsMessageExtractor());
  }

  public enum Column implements com.sarality.app.datastore.Column {
    _ID(new ColumnSpec(ColumnDataType.INTEGER, false, null, null)),
    THREAD_ID(new ColumnSpec(ColumnDataType.INTEGER, false)),
    ADDRESS(new ColumnSpec(ColumnDataType.TEXT, false)),
    PERSON(new ColumnSpec(ColumnDataType.TEXT, false)),
    BODY(new ColumnSpec(ColumnDataType.TEXT, false)),
    DATE(new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.EPOCH, false)),
    DATE_SENT(new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.EPOCH, false)),
    READ(new ColumnSpec(ColumnDataType.BOOLEAN, false)),
    STATUS(new ColumnSpec(ColumnDataType.TEXT, false)),
    TYPE(new ColumnSpec(ColumnDataType.INTEGER, false));

    private ColumnConfig<?> config;

    private Column(ColumnSpec spec) {
      this.config = new ColumnConfig(spec, null);
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

  
  public static class SmsMessageExtractor implements CursorDataExtractor<SmsMessage> {

    private Extractors extractors = new Extractors().withExtractorForMappedEnum(MessageType.values());

    @Override
    public SmsMessage extract(Cursor cursor, Query query) {
      SmsMessage.Builder builder = new SmsMessage.Builder();
      // TODO(abhideep): Use builder.setValue instead of explicit calls to methods

      if (extractors.hasColumn(cursor, Column._ID)) {
        builder.setMessageId(extractors.getLong(cursor, Column._ID));
      }
      if (extractors.hasColumn(cursor, Column.THREAD_ID)) {
        builder.setThreadId(extractors.getLong(cursor, Column.THREAD_ID));
      }
      if (extractors.hasColumn(cursor, Column.BODY)) {
        builder.setBody(extractors.getString(cursor, Column.BODY));
      }
      if (extractors.hasColumn(cursor, Column.ADDRESS)) {
        builder.setAddress(extractors.getString(cursor, Column.ADDRESS));
      }
      if (extractors.hasColumn(cursor, Column.DATE)) {
        builder.setReceivedDate(extractors.getDate(cursor, Column.DATE));
      }
      if (extractors.hasColumn(cursor, Column.DATE_SENT)) {
        builder.setSentDate(extractors.getDate(cursor, Column.DATE_SENT));
      }
      if (extractors.hasColumn(cursor, Column.READ)) {
        builder.setRead(extractors.getBoolean(cursor, Column.READ));
      }
      if (extractors.hasColumn(cursor, Column.TYPE)) {
        builder.setMessageType(Extractors.<MessageType>getEnumValue(
                cursor, Column.TYPE, extractors.getExtractor(MessageType.class)));
      }
      return builder.build();
    }    
  }
}
