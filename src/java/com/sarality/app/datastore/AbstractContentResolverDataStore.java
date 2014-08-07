package com.sarality.app.datastore;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.sarality.app.datastore.extractor.CursorDataExtractor;
import com.sarality.app.datastore.query.Query;

public abstract class AbstractContentResolverDataStore<T> extends AbstractDataStore<T> implements DataStore<T> {

  private static final String TAG = "AbstractContentResolverDataStore";

  public AbstractContentResolverDataStore(Context context, String name, List<Column> columnList,
      CursorDataExtractor<T> extractor) {
    super(context, name, columnList, extractor);
  }

  public abstract Uri getQueryUri(Query query);

  @Override
  public List<T> query(Query query) {
    Uri uri = getQueryUri(query);
    ContentResolver contentResolver = getApplicationContext().getContentResolver();

    Cursor cursor = null;
    List<T> dataList = new ArrayList<T>();
    try {
      // TODO(abhideep): Add Query support here
      cursor = contentResolver.query(uri, null, null, null, null);
      CursorDataExtractor<T> extractor = getCursorDataExtractor();

      cursor.moveToFirst();
      while (!cursor.isAfterLast()) {
        T data = extractor.extract(cursor, query);
        dataList.add(data);
        cursor.moveToNext();
      }
    } catch (Throwable t) {
      Log.e(TAG, "Error Reading data from Content Resolver", t);
      throw new RuntimeException(t);
    } finally {
      // Close the cursor to perform cleanup
      if (cursor != null && !cursor.isClosed()) {
        cursor.close();
      }
    }
    return dataList;
  }
}
