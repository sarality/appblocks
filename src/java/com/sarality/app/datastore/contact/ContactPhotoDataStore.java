package com.sarality.app.datastore.contact;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;

import com.sarality.app.datastore.AbstractContentResolverDataStore;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.CursorDataExtractor;
import com.sarality.app.datastore.query.Query;

/**
 * DataStore to store Photo of the contact 
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ContactPhotoDataStore extends AbstractContentResolverDataStore<InputStream> {

  public static final String DATASTORE_NAME = "ContactPhotoDataStore";

  private final Uri baseUri = Contacts.CONTENT_URI;
  private final Context context;
  private final Long contactId;

  /**
   * Constructor
   * 
   * @param context : Application context
   */
  public ContactPhotoDataStore(Context context, Long contactId) {
    super(DATASTORE_NAME, Arrays.<com.sarality.app.datastore.Column> asList(Column.values()),
        new ContactPhotoDataExtractor());
    this.context = context;
    this.contactId = contactId;
  }

  /**
   * Defines the list of columns specific to the
   * 
   * @author sunayna@ (Sunayna Uberoy)
   */
  public enum Column implements com.sarality.app.datastore.Column {
    PHOTO(new ColumnSpec(ColumnDataType.INTEGER, false, null, null));

    private ColumnSpec spec;

    private Column(ColumnSpec spec) {
      this.spec = spec;
    }

    @Override
    public String getName() {
      if (name().equals(Column.PHOTO.name())) {
        return Contacts.Photo.PHOTO;
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
    Uri uri = ContentUris.withAppendedId(baseUri, contactId);
    return uri;
  }

  @Override
  public ContentResolver getContentResolver() {
    return context.getApplicationContext().getContentResolver();
  }

  @Override
  public List<InputStream> query(Query query) {
    InputStream input = Contacts.openContactPhotoInputStream(context.getContentResolver(), getQueryUri(null));
    List<InputStream> inputStream = new ArrayList<InputStream>();
    inputStream.add(input);
    return inputStream;
  }

  /**
   * Extracts the inputStream for the photo given the contactID
   * @author sunayna(Sunayna Uberoy)
   *
   */
  public static class ContactPhotoDataExtractor implements CursorDataExtractor<InputStream> {

    @Override
    public InputStream extract(Cursor cursor, Query query) {
      byte[] data = cursor.getBlob(0);
      if (data != null) {
        return new ByteArrayInputStream(data);
      }
      return null;
    }
  }
}
