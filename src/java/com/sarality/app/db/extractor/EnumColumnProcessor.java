package com.sarality.app.db.extractor;

import android.database.Cursor;

import com.sarality.app.data.DataObject;
import com.sarality.app.db.Column;

public abstract class EnumColumnProcessor<B extends DataObject.Builder<?>, E extends Enum<E>>
    extends ColumnProcessor<B, E> {

  private final Class<E> enumType;

  public EnumColumnProcessor(Class<E> enumType) {
    this.enumType = enumType;
  }

  @Override
  public E extract(Cursor cursor, Column column) {
    return Enum.valueOf(enumType, "testing");
  }
}
