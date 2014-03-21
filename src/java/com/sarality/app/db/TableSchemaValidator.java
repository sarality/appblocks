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
    for(DatabaseColumn column : table.getColumns()) {
      Set<DatabaseColumn.Property> properties = column.getProperties();
      boolean isPrimaryKeyColumn = properties.contains(DatabaseColumn.Property.PRIMARY_KEY);

      DatabaseColumn.DataType dataType = column.getDataType();
      DatabaseColumn.DataTypeFormat dataTypeFormat = column.getFormat();

      if (dataTypeFormat != null && !dataType.isSupportedFormat(dataTypeFormat)) {
        throw new ValidationException("Column with name " + column.getName() 
            + " is improperly defined. The data type for the column is " + dataType
            + " which does not support Data Type " + dataTypeFormat);        
      }

      if (isPrimaryKeyColumn && !column.isRequired()) {
        throw new ValidationException("Column with name " + column.getName() 
            + " is part of the Primary key but not marked as Required."
            + " All Primary Key Columns must be marked as required.");
      }

      boolean isAutoIncrement = properties.contains(DatabaseColumn.Property.AUTO_INCREMENT);

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
          && column.getDataType() != DatabaseColumn.DataType.INTEGER) {
        throw new ValidationException("Column with name " + column.getName() 
            + " is marked as AUTO INCREMENT which is only allowed for INTEGER columns."
            + " However the data type of the column is " + column.getDataType());
      }
    }
    return true;
  }
}
