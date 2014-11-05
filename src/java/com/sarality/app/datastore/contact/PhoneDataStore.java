package com.sarality.app.datastore.contact;

import java.util.Arrays;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.sarality.app.datastore.AbstractContentResolverDataStore;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.CursorDataExtractor;
import com.sarality.app.datastore.column.ColumnProcessors;
import com.sarality.app.datastore.query.Query;

/**
 * DataStore to store all information on the PhoneNumbers of the contact
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class PhoneDataStore extends AbstractContentResolverDataStore<String> {

  public static final String DATASTORE_NAME = "PhoneDataStore";

  private final Uri baseUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
  private final Context context;

  /**
   * Constructor
   * 
   * @param context : Application context
   */
  public PhoneDataStore(Context context) {
    super(DATASTORE_NAME, Arrays.<com.sarality.app.datastore.Column> asList(Column.values()), new PhoneExtractor());
    this.context = context;
  }

  /**
   * Defines the list of columns specific to the
   * 
   * @author sunayna@ (Sunayna Uberoy)
   */
  public enum Column implements com.sarality.app.datastore.Column {
    CONTACT_ID(new ColumnSpec(ColumnDataType.INTEGER, false, null, null)),
    NUMBER(new ColumnSpec(ColumnDataType.TEXT, false));

    private ColumnSpec spec;

    private Column(ColumnSpec spec) {
      this.spec = spec;
    }

    @Override
    public String getName() {
      if (name().equals(Column.CONTACT_ID.name())) {
        return ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
      } else if (name().equals(Column.NUMBER.name())) {
        return ContactsContract.CommonDataKinds.Phone.NUMBER;
      }
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

  public static class PhoneExtractor implements CursorDataExtractor<String> {

    private final ColumnProcessors processors = new ColumnProcessors();

    @Override
    public String extract(Cursor cursor, Query query) {
      Log.d("", "Extracting number.." + processors.forString().extract(cursor, Column.NUMBER));
      return (processors.forString().extract(cursor, Column.NUMBER));
    }
  }
}
