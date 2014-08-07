package com.sarality.app.datastore.sms;

import java.util.Arrays;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import com.sarality.app.data.FieldBasedDataObject.Builder;
import com.sarality.app.datastore.AbstractContentResolverDataStore;
import com.sarality.app.datastore.BaseFieldColumnConfigProvider;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnFormat;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.extractor.BooleanValueExtractor;
import com.sarality.app.datastore.extractor.DateValueExtractor;
import com.sarality.app.datastore.extractor.GenericCursorDataExtractor;
import com.sarality.app.datastore.extractor.LongValueExtractor;
import com.sarality.app.datastore.extractor.MappedEnumValueExtractor;
import com.sarality.app.datastore.extractor.StringValueExtractor;
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
    DATE_SENT(new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.EPOCH, false)),
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

  /**
   * Providing a list of configuration objects that define the relationship between SmsDataStore columns and fields in
   * the SmsMessage data object.
   * 
   * @author abhideep@ (Abhideep Singh)
   */
  private static class FieldColumnConfigProvider extends BaseFieldColumnConfigProvider {

    private FieldColumnConfigProvider() {
      super();
      addEntry(SmsMessageField.MESSAGE_ID, Column._ID, new LongValueExtractor(), null);
      addEntry(SmsMessageField.THREAD_ID, Column.THREAD_ID, new LongValueExtractor(), null);
      addEntry(SmsMessageField.ADDRESS, Column.ADDRESS, new StringValueExtractor(), null);
      addEntry(SmsMessageField.BODY, Column.BODY, new StringValueExtractor(), null);
      addEntry(SmsMessageField.RECEIVED_DATE, Column.DATE, new DateValueExtractor(), null);
      addEntry(SmsMessageField.SENT_DATE, Column.DATE_SENT, new DateValueExtractor(), null);
      addEntry(SmsMessageField.IS_READ, Column.READ, new BooleanValueExtractor(), null);
      addEntry(SmsMessageField.MESSAGE_TYPE, Column.TYPE, new MappedEnumValueExtractor<Integer, MessageType>(
          MessageType.values()), null);
      // TODO(abhideep): Add config for Status and Person
    }
  }

  @Override
  public ContentResolver getContentResolver() {
    return context.getApplicationContext().getContentResolver();
  }

  public static class SmsMessageExtractor extends GenericCursorDataExtractor<SmsMessage> {

    public SmsMessageExtractor() {
      super(new FieldColumnConfigProvider().provide());
    }

    @Override
    protected Builder<SmsMessage> newBuilder() {
      return new SmsMessage.Builder();
    }
  }
}
