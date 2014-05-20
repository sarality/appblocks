package com.sarality.app.datastore.extractor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.MappedEnum;

/**
 * Utility class that contains instances of the extractors of various types
 * for easy access in the concrete implementations of CursorDataExtractor.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class Extractors {
  private final BooleanValueExtractor booleanExtractor = new BooleanValueExtractor();
  private final DateValueExtractor dateExtractor = new DateValueExtractor();
  private final DoubleValueExtractor doubleExtractor = new DoubleValueExtractor();
  private final IntegerValueExtractor integerExtractor = new IntegerValueExtractor();
  private final LongValueExtractor longExtractor = new LongValueExtractor();
  private final StringValueExtractor stringExtractor = new StringValueExtractor();
  
  private final Map<Class<?>, ColumnValueExtractor<?>> valueExtractorMap = new HashMap<Class<?>, ColumnValueExtractor<?>>();

  public Extractors() {
    register(Boolean.class, booleanExtractor);
    register(Date.class, dateExtractor);
    register(Double.class, doubleExtractor);
    register(Integer.class, integerExtractor);
    register(Long.class, longExtractor);
    register(String.class, stringExtractor);
  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Extractors withExtractorForEnums(List<Class<? extends Enum<?>>> enumClassList) {
    for (Class<? extends Enum<?>> enumClass : enumClassList) {
      register(enumClass, new EnumValueExtractor(enumClass));      
    }
    return this;
  }
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public Extractors withExtractorForMappedEnum(MappedEnum<?>[] enumValues) {
    if (enumValues != null && enumValues.length > 0) {
      register(enumValues[0].getClass(), new MappedEnumValueExtractor(enumValues));
    }
    return this;
  }

  private void register(Class<?> className, ColumnValueExtractor<?> extractor) {
    valueExtractorMap.put(className, extractor);
  }
  
  public ColumnValueExtractor<?> getExtractor(Class<?> className) {
    return valueExtractorMap.get(className);
  }
  
  public static <T extends Enum<T>> T getEnumValue(Cursor cursor, Column column, ColumnValueExtractor<?> extractor) {
    @SuppressWarnings("unchecked")
    T value = (T) extractor.extract(cursor, column);
    return value;
  }
  
  /**
   * Indicates whether the given Column is returned by the cursor.
   * 
   * @param cursor The cursor that we are extracting values from.
   * @param query The query that generated the cursor.
   * @param column The that we are checking for
   * @return {@code true} if the column exists in the cursor, {@code false} otherwise.
   */
  public boolean hasColumn(Cursor cursor, Column column) {
    return cursor.getColumnIndex(column.getName()) > -1;
  }

  public Boolean getBoolean(Cursor cursor, Column column) {
    return booleanExtractor.extract(cursor, column);
  }

  public Date getDate(Cursor cursor, Column column) {
    return dateExtractor.extract(cursor, column);
  }

  public Double getDouble(Cursor cursor, Column column) {
    return doubleExtractor.extract(cursor, column);
  }
  
  public Integer getInteger(Cursor cursor, Column column) {
    return integerExtractor.extract(cursor, column);
  }

  public Long getLong(Cursor cursor, Column column) {
    return longExtractor.extract(cursor, column);
  }

  public String getString(Cursor cursor, Column column) {
    return stringExtractor.extract(cursor, column);
  }
}
