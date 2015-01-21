package com.sarality.app.datastore.contact;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;

import com.sarality.app.common.data.user.PersonNameDataBuilder;
import com.sarality.app.datastore.AbstractContentResolverDataStore;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.column.ColumnProcessor;
import com.sarality.app.datastore.column.ColumnProcessors;
import com.sarality.app.datastore.contact.ContactNumber.ContactLabel;
import com.sarality.app.datastore.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DataStore to store information about the contact
 *
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ContactDataStore extends AbstractContentResolverDataStore<ContactData> {

  public static final String DATASTORE_NAME = "ContactDataStore";
  private static final String staticWhereClause = Column.HAS_PHONE_NUMBER + " !=0 AND (" + Column.MIMETYPE + " = ? OR "
      + Column.MIMETYPE + " = ? OR " + Column.MIMETYPE + " = ? OR " + Column.MIMETYPE + " = ? )";
  private final Context context;

  /**
   * Constructor
   *
   * @param context : Application context
   */
  public ContactDataStore(Context context) {
    super(DATASTORE_NAME, Arrays.<com.sarality.app.datastore.Column>asList(Column.values()), null);
    this.context = context;
  }

  // TODO Add SqlName in ColumnSpec

  @Override
  public Uri getQueryUri(Query query) {
    return Data.CONTENT_URI;
  }

  @Override
  public ContentResolver getContentResolver() {
    return context.getApplicationContext().getContentResolver();
  }

  @Override
  public List<ContactData> query(Query query) {
    Cursor cursor = getContentResolver().query(getQueryUri(null), null, buildWhereClause(query),
        buildWhereClauseValues(query), Column.CONTACT_ID.name());
    return buildContactList(cursor);
  }

  /**
   * Concatenates the default where clause with the query sent as a parameter
   *
   * @param query Additional Query on ContactDataStore
   * @return Concatenated whereClause String
   */
  private String buildWhereClause(Query query) {
    StringBuilder stringBuilder = new StringBuilder(staticWhereClause);

    if (query != null) {
      stringBuilder.append("AND ");
      stringBuilder.append(query.getWhereClause());
    }
    return stringBuilder.toString();
  }

  /**
   * Builds the whereClause Values by adding default WhereClauseValues and WhereClauseValues as a part of the query
   *
   * @param query Additional Query on ContactDataStore
   * @return new WhereClauseValues String[]
   */
  private String[] buildWhereClauseValues(Query query) {
    List<String> whereClauseValues = new ArrayList<String>();
    whereClauseValues.add(Email.CONTENT_ITEM_TYPE);
    whereClauseValues.add(Phone.CONTENT_ITEM_TYPE);
    whereClauseValues.add(StructuredName.CONTENT_ITEM_TYPE);

    for (AppEnum app : AppEnum.values()) {
      whereClauseValues.add(app.getMimeType());
    }

    if (query != null && query.getWhereClauseValues() != null) {
      whereClauseValues.addAll(Arrays.asList(query.getWhereClauseValues()));
    }

    return whereClauseValues.toArray(new String[whereClauseValues.size()]);
  }

  /**
   * Builds the ContactDataList extracting relavant information from the cursor
   *
   * @param cursor Cursor with the query result
   * @return List of contacts
   */
  private List<ContactData> buildContactList(Cursor cursor) {
    Long oldContactId = 0l;
    final ColumnProcessors processors = new ColumnProcessors();
    List<ContactData> dataList = new ArrayList<ContactData>();

    cursor.moveToFirst();

    ContactDataBuilder builder = null;
    while (!cursor.isAfterLast()) {
      long contactId = processors.forInteger().extract(cursor, Column.CONTACT_ID);
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
    final ColumnProcessor<ContactLabel> labelTypeProcessor = processors.forMappedEnum(ContactLabel.class, ContactLabel.values());

    builder.setContactId(contactId);

    builder.setPhotoId(processors.forInteger().extract(cursor, Column.PHOTO_ID));
    String mimeType = processors.forString().extract(cursor, Column.MIMETYPE);
    String data = processors.forString().extract(cursor, Column.DATA1);
    int isPrimary = processors.forInteger().extract(cursor, Column.IS_PRIMARY);
    if (mimeType.equals(Email.CONTENT_ITEM_TYPE)) {
      builder.addEmailId(data);
    } else if (mimeType.equals(Phone.CONTENT_ITEM_TYPE)) {
      ContactLabel labelType = labelTypeProcessor.extract(cursor, Column.DATA2);
      builder.addPhoneNumber(new ContactNumber(data, isPrimary > 0, labelType));
    } else if (mimeType.equals(StructuredName.CONTENT_ITEM_TYPE)) {
      PersonNameDataBuilder nameBuilder = new PersonNameDataBuilder();
      nameBuilder.setDisplayName(data);
      nameBuilder.setGivenName(processors.forString().extract(cursor, Column.DATA2));
      nameBuilder.setFamilyName(processors.forString().extract(cursor, Column.DATA3));
      nameBuilder.setPrefix(processors.forString().extract(cursor, Column.DATA4));
      nameBuilder.setMiddleName(processors.forString().extract(cursor, Column.DATA5));
      nameBuilder.setSuffix(processors.forString().extract(cursor, Column.DATA6));
      builder.setName(nameBuilder);
    } else {
      AppEnum appEnum = AppEnum.getAppEnum(mimeType);
      if (appEnum != null) {
        builder.addAppContact(new AppContact(appEnum, data, processors.forLong().extract(cursor, Column._ID)));
      }

    }
    return builder;
  }

  /**
   * Defines the list of columns specific to the
   *
   * @author sunayna@ (Sunayna Uberoy)
   */
  public enum Column implements com.sarality.app.datastore.Column {
    // Basic Contact Id for each contact listed in the System
    CONTACT_ID(new ColumnSpec(ColumnDataType.INTEGER, false, null, null)),
    // Data ID associated with each data kind , Phone, email etc each would have their own Data ID
    _ID(new ColumnSpec(ColumnDataType.INTEGER, false, null, null)),
    // Name of the person
    DISPLAY_NAME(new ColumnSpec(ColumnDataType.TEXT, false)),
    //DATA1 is an indexed column and should be used for the data element that is expected to be most frequently used
    // in query selections.
    DATA1(new ColumnSpec(ColumnDataType.TEXT, false)),
    // Data2 - Data6 auxillary data associated with each mimetype
    DATA2(new ColumnSpec(ColumnDataType.TEXT, false)),
    DATA3(new ColumnSpec(ColumnDataType.TEXT, false)),
    DATA4(new ColumnSpec(ColumnDataType.TEXT, false)),
    DATA5(new ColumnSpec(ColumnDataType.TEXT, false)),
    DATA6(new ColumnSpec(ColumnDataType.TEXT, false)),
    // Primary entry for the contact
    IS_PRIMARY(new ColumnSpec(ColumnDataType.INTEGER, false)),
    // MimeType of the contact
    MIMETYPE(new ColumnSpec(ColumnDataType.TEXT, false)),
    // Id of the contact's photo
    PHOTO_ID(new ColumnSpec(ColumnDataType.INTEGER, false)),
    // An indicator of whether this contact has at least one phone number
    HAS_PHONE_NUMBER(new ColumnSpec(ColumnDataType.INTEGER, false));

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
}
