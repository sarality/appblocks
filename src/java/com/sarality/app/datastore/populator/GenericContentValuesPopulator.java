package com.sarality.app.datastore.populator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.ContentValues;
import android.util.Log;

import com.sarality.app.data.Field;
import com.sarality.app.data.FieldBasedDataObject;
import com.sarality.app.data.FieldData;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.FieldColumnMapping;

public class GenericContentValuesPopulator<T extends FieldBasedDataObject<T>> implements ContentValuesPopulator<T> {
  private final List<FieldColumnMapping> mappingList = new ArrayList<FieldColumnMapping>();
  private Map<Field, FieldColumnMapping> fieldMappingMap = new HashMap<Field, FieldColumnMapping>();
  private Map<Column, FieldColumnMapping> columnMappingMap = new HashMap<Column, FieldColumnMapping>();

  public GenericContentValuesPopulator(List<FieldColumnMapping> mappingList) {
    this.mappingList.addAll(mappingList);
    for (FieldColumnMapping mapping : mappingList) {
      fieldMappingMap.put(mapping.getField(), mapping);
      columnMappingMap.put(mapping.getColumn(), mapping);
    }
  }

  @Override
  public void populate(ContentValues contentValues, T data) {
    Set<Field> fieldSet = data.getFields();
    Log.i("GenericContentValuesPopulator", "Data has the following field " + fieldSet);
    for (Field field : fieldSet) {
      Log.i("GenericContentValuesPopulator", "Populating value for field " + field.getName() + " with value " + data.getValue(field));
      if (data.hasValue(field)) {
        FieldColumnMapping mapping = fieldMappingMap.get(field);
        FieldData<?> value = data.getFieldData(field);
        FieldDataValuePopulator populator = mapping.getPopulator();
        populator.populate(contentValues, mapping.getColumn(), value);
      }
    }
  }
}
