package com.sarality.app.datastore.db;

import com.sarality.app.datastore.ColumnProperty;

/**
 * Properties associated with the database columns
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public enum TableColumnProperty implements ColumnProperty {
  AUTO_INCREMENT,
  PRIMARY_KEY,
  ;

  @Override
  public String getName() {
    return name();
  }

  @Override
  public boolean equals(ColumnProperty property) {
    if (property instanceof TableColumnProperty) {
      TableColumnProperty tableProperty = (TableColumnProperty) property;
      return tableProperty == this;
    }
    return false;
  }
}
