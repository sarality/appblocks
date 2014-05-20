package com.sarality.app.datastore.populator;

import com.sarality.app.data.FieldData;
import com.sarality.app.datastore.Column;

import android.content.ContentValues;

public interface FieldDataValuePopulator {

  public void populate(ContentValues values, Column column, FieldData<?> data);
}
