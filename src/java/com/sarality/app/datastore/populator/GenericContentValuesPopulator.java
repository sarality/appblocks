package com.sarality.app.datastore.populator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.ContentValues;

import com.sarality.app.data.Field;
import com.sarality.app.data.FieldBasedDataObject;
import com.sarality.app.data.FieldData;
import com.sarality.app.datastore.FieldColumnConfig;

public class GenericContentValuesPopulator<T extends FieldBasedDataObject<T>> implements ContentValuesPopulator<T> {
  private Map<Field, FieldColumnConfig> fieldConfigMap = new HashMap<Field, FieldColumnConfig>();

  public GenericContentValuesPopulator(List<FieldColumnConfig> configList) {
    for (FieldColumnConfig config : configList) {
      fieldConfigMap.put(config.getField(), config);
    }
  }

  @Override
  public void populate(ContentValues contentValues, T data) {
    Set<Field> fieldSet = data.getFields();
    for (Field field : fieldSet) {
      if (data.hasValue(field)) {
        FieldColumnConfig config = fieldConfigMap.get(field);
        FieldData<?> value = data.getFieldData(field);
        FieldDataValuePopulator populator = config.getPopulator();
        populator.populate(contentValues, config.getColumn(), value);
      }
    }
  }
}
