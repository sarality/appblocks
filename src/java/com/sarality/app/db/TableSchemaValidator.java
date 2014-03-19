package com.sarality.app.db;

import java.util.Set;

import com.sarality.app.error.ValidationException;

/**
 * Validates the Schema for the given database table.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class TableSchemaValidator {

  /**
   * Validates the Schema for the given database table.
   * 
   * @param table Table whose schema needs to be validated.
   * @return true is the schema is valid, false otherwise
   * @throws ValidationException when there is a problem with the schema
   */
  public boolean validate(DatabaseTable<?> table) throws ValidationException {
    TableMetadata metadata = table.getMetadata();
    boolean hasCompositePrimaryKey = metadata.hasCompositePrimaryKey();
    for(Column column : table.getColumns()) {
      Set<Column.Property> properties = column.getProperties();
      boolean isPrimaryKeyColumn = properties.contains(Column.Property.PRIMARY_KEY);

      if (isPrimaryKeyColumn && !column.isRequired()) {
        throw new ValidationException("Column with name " + column.getName() 
            + " is part of the Primary key but not marked as Required."
            + " All Primary Key Columns must be marked as required.");
      }

      boolean isAutoIncrement = properties.contains(Column.Property.AUTO_INCREMENT);

      if (isAutoIncrement && !isPrimaryKeyColumn) {
        throw new ValidationException("Column with name " + column.getName()
            + " is marked as AUTO INCREMENT but is not the Primary Key." 
            + " Auto Increment can only be used with an Integer Primary Key");
      }

      if (isAutoIncrement && isPrimaryKeyColumn && hasCompositePrimaryKey) {
        throw new ValidationException("Column with name " + column.getName()
            + " is part of the Composite Primary key and also marked as AUTO INCREMENT." 
            + " A Column that is part of a Composite Primary Key cannot be marked for AUTO INCREMENT");
      }

      if (isAutoIncrement && isPrimaryKeyColumn && !hasCompositePrimaryKey
          && column.getDataType() != Column.DataType.INTEGER) {
        throw new ValidationException("Column with name " + column.getName() 
            + " is marked as AUTO INCREMENT which is only allowed for INTEGER columns."
            + " However the data type of the column is " + column.getDataType());
      }
    }
    return true;
  }
}
