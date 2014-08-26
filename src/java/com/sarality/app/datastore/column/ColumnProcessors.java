package com.sarality.app.datastore.column;

import hirondelle.date4j.DateTime;

import java.util.Date;

import com.sarality.app.data.EnumData;
import com.sarality.app.data.field.Field.DataType;

/**
 * Provides ColumnProcessors for a given type of Column.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class ColumnProcessors {
  
  private final ColumnProcessor<Boolean> booleanColumnProcessor = new BooleanColumnProcessor();

  private final ColumnProcessor<Date> dateColumnProcessor = new DateColumnProcessor();

  private final ColumnProcessor<DateTime> dateOnlyColumnProcessor = new DateTimeColumnProcessor(DataType.DATE_ONLY);

  private final ColumnProcessor<DateTime> dateTimeColumnProcessor = new DateTimeColumnProcessor(DataType.DATETIME);

  private final ColumnProcessor<Double> doubleColumnProcessor = new DoubleColumnProcessor();

  private final ColumnProcessor<Integer> integerColumnProcessor = new IntegerColumnProcessor();

  private final ColumnProcessor<Long> longColumnProcessor = new LongColumnProcessor();

  private final ColumnProcessor<String> stringColumnProcessor = new StringColumnProcessor();
  
  private final ColumnProcessor<DateTime> timeOnlyColumnProcessor = new DateTimeColumnProcessor(DataType.TIME_ONLY);

  
  public final ColumnProcessor<Boolean> forBoolean() {
    return booleanColumnProcessor;
  }

  public final ColumnProcessor<Date> forDate() {
    return dateColumnProcessor;
  }

  public final ColumnProcessor<DateTime> forDateOnly() {
    return dateOnlyColumnProcessor;
  }

  public final ColumnProcessor<DateTime> forDateTime() {
    return dateTimeColumnProcessor;
  }
  
  public final ColumnProcessor<Double> forDouble() {
    return doubleColumnProcessor;
  }

  public final ColumnProcessor<Integer> forInteger() {
    return integerColumnProcessor;
  }

  public final ColumnProcessor<Long> forLong() {
    return longColumnProcessor;
  }

  public final ColumnProcessor<String> forString() {
    return stringColumnProcessor;
  }

  public final ColumnProcessor<DateTime> forTimeOnly() {
    return timeOnlyColumnProcessor;
  }
  
  public final <E extends Enum<E>> ColumnProcessor<E> forEnum(Class<E> enumClass) {
    return new EnumColumnProcessor<E>(enumClass);
  }

  public final <E extends EnumData<E>> ColumnProcessor<E> forEnumData(Class<E> enumClass) {
    return new EnumDataColumnProcessor<E>(enumClass);
  }
}
