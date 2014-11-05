package com.sarality.app.datastore.contact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;

import com.sarality.app.datastore.AbstractContentResolverDataStore;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.column.ColumnProcessors;
import com.sarality.app.datastore.query.Query;

/**
 * DataStore to store information about the contact
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ContactDataStore extends AbstractContentResolverDataStore<ContactData> {

  public static final String DATASTORE_NAME = "ContactDataStore";
  private final Context context;

  private final Uri baseUri = ContactsContract.Contacts.CONTENT_URI;

  /**
   * Constructor
   * 
   * @param context : Application context
   */
  public ContactDataStore(Context context) {
    super(DATASTORE_NAME, Arrays.<com.sarality.app.datastore.Column> asList(Column.values()), null);
    this.context = context;
  }

  /**
   * Defines the list of columns specific to the
   * 
   * @author sunayna@ (Sunayna Uberoy)
   */
  public enum Column implements com.sarality.app.datastore.Column {
    contact_id(new ColumnSpec(ColumnDataType.INTEGER, false, null, null)),
    display_name(new ColumnSpec(ColumnDataType.TEXT, false)),
    is_primary(new ColumnSpec(ColumnDataType.INTEGER, false)),
    mimetype(new ColumnSpec(ColumnDataType.TEXT, false)),
    data1(new ColumnSpec(ColumnDataType.TEXT, false)),
    photo_id(new ColumnSpec(ColumnDataType.INTEGER, false)),
    has_phone_number(new ColumnSpec(ColumnDataType.INTEGER, false));

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
    ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
    return contentResolver;
  }

  @Override
  public List<ContactData> query(Query query) {
    Cursor cursor = getContentResolver().query(Data.CONTENT_URI, null, buildWhereClause(query),
        buildWhereClauseValues(query), Column.contact_id.name());
    return buildContactList(cursor);
  }

  /**
   * Concatenates the default where clause with the query sent as a parameter
   * 
   * @param query
   * @return Concatenated whereClause String
   */
  private String buildWhereClause(Query query) {
    String whereClause = Column.has_phone_number + " !=0 AND (" + Column.mimetype + " = ? OR " + Column.mimetype
        + " = ? ) ";

    if (query != null) {
      return (whereClause + " AND " + query.getWhereClause());
    }
    return whereClause;
  }

  /**
   * Builds the whereClause Values by adding default WhereClauseValues and WhereClauseValues as a part of the query
   * 
   * @param query
   * @return new WhereClauseValues String[]
   */
  private String[] buildWhereClauseValues(Query query) {
    String[] whereClauseValues = new String[] { Email.CONTENT_ITEM_TYPE, Phone.CONTENT_ITEM_TYPE };

    String[] concatWhereClauseValue;

    if (query != null) {
      concatWhereClauseValue = Arrays.copyOf(whereClauseValues, whereClauseValues.length
          + query.getWhereClauseValues().length);
      System.arraycopy(query.getWhereClauseValues(), 0, concatWhereClauseValue, whereClauseValues.length,
          query.getWhereClauseValues().length);
    } else {
      concatWhereClauseValue = whereClauseValues;
    }
    return concatWhereClauseValue;
  }

  /**
   * Builds the ContactDataList extracting relavant information from the cursor
   * 
   * @param cursor
   * @return List of contacts
   */
  private List<ContactData> buildContactList(Cursor cursor) {
    Long oldContactId = 0l;
    final ColumnProcessors processors = new ColumnProcessors();
    List<ContactData> dataList = new ArrayList<ContactData>();

    cursor.moveToFirst();
    ContactDataBuilder builder = null;
    while (!cursor.isAfterLast()) {
      long contactId = processors.forInteger().extract(cursor, Column.contact_id);
      if (contactId != oldContactId) {
        if (builder != null) {
          dataList.add(builder.build());
        }
        builder = new ContactDataBuilder();
      }
      builder = extractContactData(builder, contactId, cursor);
      oldContactId = contactId;
      cursor.moveToNext();
    }
    if (builder != null) {
      dataList.add(builder.build());
    }
    return dataList;
  }

  /**
   * Extracts the ContactData information from the cursor
   * 
   * @param builder It could be the same contact
   * @param contactId contactId for the contact
   * @param cursor Cursor from which the rest of the contactData is extracted
   * @return ContactDataBuilder with extracted data
   */
  private ContactDataBuilder extractContactData(ContactDataBuilder builder, long contactId, Cursor cursor) {
    final ColumnProcessors processors = new ColumnProcessors();

    builder.setContactId(contactId);
    builder.setName(processors.forString().extract(cursor, Column.display_name));
    builder.setPhotoId(processors.forInteger().extract(cursor, Column.photo_id));
    String mimeType = processors.forString().extract(cursor, Column.mimetype);
    String data = processors.forString().extract(cursor, Column.data1);
    int isPrimary = processors.forInteger().extract(cursor, Column.is_primary);
    if (mimeType.equals(Email.CONTENT_ITEM_TYPE)) {
      builder.addEmailId(data);
    } else if (mimeType.equals(Phone.CONTENT_ITEM_TYPE)) {
      builder.addPhoneNumber(new ContactNumber(data, isPrimary > 0 ? true : false));
    }
    return builder;
  }
}
