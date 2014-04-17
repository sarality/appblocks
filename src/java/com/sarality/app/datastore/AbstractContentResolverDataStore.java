package com.sarality.app.datastore;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.sarality.app.data.DataObject;
import com.sarality.app.datastore.extractor.CursorDataExtractor;

public abstract class AbstractContentResolverDataStore<T extends DataObject<T>>
    extends AbstractDataStore<T> implements DataStore<T> {

  public AbstractContentResolverDataStore(Context context, List<Column> columnList, CursorDataExtractor<T> extractor) {
    super(context, columnList, extractor);
  }

  public abstract Uri getQueryUri(Query query);

  @Override
  public List<T> query(Query query) {
    Uri uri = getQueryUri(query);
    ContentResolver contentResolver = getApplicationContext().getContentResolver();

    // TODO(abhideep): Add Query support here
    Cursor cursor = contentResolver.query(uri, null, null, null, null);
    CursorDataExtractor<T> extractor = getCursorDataExtractor();
    List<T> dataList = new ArrayList<T>();

    cursor.moveToFirst();
    for (String columnName : cursor.getColumnNames()) {
      Log.w(getLoggerTag(), "Column Name " + columnName);      
    }

    while (!cursor.isAfterLast()) {
      T data = extractor.extract(cursor, query);
      dataList.add(data);
      cursor.moveToNext();
    }

    // Close the cursor to perform cleanup
    cursor.close();
    return dataList;
  }
}
