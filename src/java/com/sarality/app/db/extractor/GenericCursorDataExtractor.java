package com.sarality.app.db.extractor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;

import com.sarality.app.data.DataObject;
import com.sarality.app.db.Column;
import com.sarality.app.db.Query;

public abstract class GenericCursorDataExtractor<T extends DataObject<T>> implements CursorDataExtractor<T> {
  private final Map<Column, ColumnProcessor<DataObject.Builder<T>, ?>> processorMap = 
      new HashMap<Column, ColumnProcessor<DataObject.Builder<T>, ?>>();

  public final void register(Column column, ColumnProcessor<DataObject.Builder<T>, ?> processor) {
    processorMap.put(column, processor);
  }

  public final ColumnProcessor<DataObject.Builder<T>, ?> getProcessor(Column column) {
    return processorMap.get(column);
  }  

  public abstract void init();

  public abstract DataObject.Builder<T> newBuilder();

  @Override
  public T extract(Cursor cursor, Query query) {
    DataObject.Builder<T> builder = newBuilder();
    List<Column> columnList = query.getColumns();
    for (Column column : columnList) {
      ColumnProcessor<DataObject.Builder<T>, ?> processor = getProcessor(column);
      processor.extractAndSetValue(cursor, column, builder);
    }
    return builder.build();
  }

}
