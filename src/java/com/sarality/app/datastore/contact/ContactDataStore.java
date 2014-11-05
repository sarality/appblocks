package com.sarality.app.datastore.contact;

import java.util.Arrays;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.sarality.app.datastore.AbstractContentResolverDataStore;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.CursorDataExtractor;
import com.sarality.app.datastore.column.ColumnProcessors;
import com.sarality.app.datastore.query.Query;

/**
 * DataStore to store information about the contact (contact Id, name and photoID)
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ContactDataStore extends AbstractContentResolverDataStore<ContactData> {

  public static final String DATASTORE_NAME = "ContactDataStore";
  private final Context context;
  private static ContentResolver contentResolver;

  private final Uri baseUri = ContactsContract.Contacts.CONTENT_URI;

  /**
   * Constructor
   * 
   * @param context : Application context
   */
  public ContactDataStore(Context context) {
    super(DATASTORE_NAME, Arrays.<com.sarality.app.datastore.Column> asList(Column.values()), new ContactExtractor());
    this.context = context;
  }

  /**
   * Defines the list of columns specific to the
   * 
   * @author sunayna@ (Sunayna Uberoy)
   */
  public enum Column implements com.sarality.app.datastore.Column {
    CONTACT_ID(new ColumnSpec(ColumnDataType.INTEGER, false, null, null)),
    DISPLAY_NAME(new ColumnSpec(ColumnDataType.TEXT, false)),
    HAS_PHONE_NUMBER(new ColumnSpec(ColumnDataType.INTEGER, false)),
    PHOTO_ID(new ColumnSpec(ColumnDataType.TEXT, false));

    private ColumnSpec spec;

    private Column(ColumnSpec spec) {
      this.spec = spec;
    }

    @Override
    public String getName() {

      if (name().equals(CONTACT_ID.name())) {
        return ContactsContract.Contacts._ID;
      }
      if (name().equals(DISPLAY_NAME.name())) {
        return ContactsContract.Contacts.DISPLAY_NAME;
      }
      if (name().equals(HAS_PHONE_NUMBER.name())) {
        return ContactsContract.Contacts.HAS_PHONE_NUMBER;
      }
      if (name().equals(PHOTO_ID.name())) {
        return ContactsContract.Contacts.PHOTO_ID;
      }
      return null;
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
    contentResolver = context.getApplicationContext().getContentResolver();
    return contentResolver;
  }

  /**
   * Extracts the Contact ID, name and PhotoId from the DB and builds ContactData
   * 
   * @author sunayna(Sunayna Uberoy)
   * 
   */
  public static class ContactExtractor implements CursorDataExtractor<ContactData> {

    private final ColumnProcessors processors = new ColumnProcessors();

    @Override
    public ContactData extract(Cursor cursor, Query query) {
      ContactDataBuilder builder = new ContactDataBuilder();

      builder.setContactId(processors.forLong().extract(cursor, Column.CONTACT_ID));
      if (processors.forInteger().extract(cursor, Column.HAS_PHONE_NUMBER) > 0) {
        builder.setHasPhoneNumber(Boolean.TRUE);
      } else {
        builder.setHasPhoneNumber(Boolean.FALSE);
      }
      builder.setName(processors.forString().extract(cursor, Column.DISPLAY_NAME));
      builder.setPhotoId(processors.forInteger().extract(cursor, Column.PHOTO_ID));
      return builder.build();
    }
  }
}
