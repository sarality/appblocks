package com.sarality.app.data.field;

import android.content.Context;

import com.sarality.app.BaseApplication;
import com.sarality.app.data.EnumData;
import com.sarality.app.data.field.Field.DataType;
import com.sarality.app.datastore.EnumDataStore;

public class EnumDataFieldValue<E extends EnumData> extends BaseFieldValue<E> {

  private final Context context;

  public EnumDataFieldValue(Field field, DataType fieldType, Class<E> valueClass, Context context) {
    super(field, DataType.ENUM, valueClass);
    this.context = context;
  }

  @Override
  public void castFrom(Object value) {
    @SuppressWarnings("unchecked")
    E enumValue = (E) value;
    setValue(enumValue);
  }

  @Override
  public void parseFrom(String stringValue) {
    BaseApplication app = BaseApplication.getApp(context);
    EnumDataStore<E> store = app.getEnumDataStore(getValueClass());
    if (store != null) {
      setValue(store.lookupByName(stringValue));
    }
  }

  @Override
  public String getStringValue() {
    return getValue().getEnumName();
  }

  public static class Factory<E extends EnumData> implements FieldValueFactory<E> { 
    private final Context context;
    private final Class<E> valueClass;
    
    public Factory(Context context, Class<E> valueClass) {
      this.context = context;
      this.valueClass = valueClass;
    }

    @Override
    public FieldValue<E> createFieldValue(Field field) {
      return new EnumDataFieldValue<E>(field, DataType.ENUM, valueClass, context);
    }
  }
}
