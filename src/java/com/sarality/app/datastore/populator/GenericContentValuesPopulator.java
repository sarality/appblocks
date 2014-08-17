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

/**
 * Generic implementation of a {@link ContentValuesPopulator} based on a mapping between DataObject Fields and
 * Database Columns.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> Type of DataObject used to populate the ContentValues.
 */
public class GenericContentValuesPopulator<T extends FieldBasedDataObject<T>> implements ContentValuesPopulator<T> {
  private Map<Field, FieldColumnConfig> fieldConfigMap = new HashMap<Field, FieldColumnConfig>();

  /**
   * Constructor.
   * 
   * @param configList A list of Mapping between Field and Column, and the configuration information on how to
   * populate the ContentValues for these Field-Column pairs.
   */
  public GenericContentValuesPopulator(List<FieldColumnConfig> configList) {
    for (FieldColumnConfig config : configList) {
      fieldConfigMap.put(config.getField(), config);
    }
  }

  @Override
  public boolean populate(ContentValues contentValues, T data) {
    Set<Field> fieldSet = data.getFields();
    for (Field field : fieldSet) {
      if (data.hasValue(field)) {
        FieldColumnConfig config = fieldConfigMap.get(field);
        FieldData<?> value = data.getFieldData(field);
        FieldDataValuePopulator populator = config.getPopulator();
        populator.populate(contentValues, config.getColumn(), value);
      }
    }
    return true;
  }
}
