package com.sarality.app.datastore.db;

import com.sarality.app.datastore.Column;

public interface TableColumn {

  /**
   * Properties associated with the database columns
   * 
   * @author abhideep@ (Abhideep Singh)
   */
  public enum Property implements Column.Property {
    AUTO_INCREMENT,
    PRIMARY_KEY,;

    @Override
    public String getName() {
      return name();
    }

    @Override
    public boolean equals(Column.Property property) {
      if (property instanceof TableColumn.Property) {
        Property tableProperty = (TableColumn.Property) property;
        return tableProperty == this;
      }
      return false;
    }
  }
}
