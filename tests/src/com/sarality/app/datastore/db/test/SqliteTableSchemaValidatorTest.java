package com.sarality.app.datastore.db.test;

import static org.mockito.Mockito.mock;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnFormat;
import com.sarality.app.datastore.ColumnProperty;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.db.SqliteTableSchemaValidator;
import com.sarality.app.datastore.db.Table;
import com.sarality.app.datastore.db.TableColumnProperty;
import com.sarality.app.datastore.db.TableInfo;
import com.sarality.app.error.ValidationException;

/**
 * Tests for {@link SqliteTableSchemaValidator}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
@RunWith(RobolectricTestRunner.class)
public class SqliteTableSchemaValidatorTest extends TestCase {

  @SuppressWarnings("unchecked")
  @Test
  public final void testValidate() {
    SqliteTableSchemaValidator validator = new SqliteTableSchemaValidator();
    TableInfo metaData = mock(TableInfo.class);
    assertNotNull(validator);
    Table<TestObject> table = mock(Table.class);
    Mockito.when(table.getTableInfo()).thenReturn(metaData);
    try {
      assertTrue(validator.validate(table));
    } catch (ValidationException e) {
      fail("Validation should not fail");
    }
  }

  private Column createColumn(ColumnDataType dataType, boolean isRequired) {
    Column column = mock(Column.class);
    Set<ColumnProperty> properties = new HashSet<ColumnProperty>();
    ColumnProperty property1 = mock(ColumnProperty.class);
    ColumnProperty property2 = mock(ColumnProperty.class);
    ColumnFormat dataTypeFormat = ColumnFormat.ENUM_AS_INT;
    property1 = TableColumnProperty.PRIMARY_KEY;
    property2 = TableColumnProperty.AUTO_INCREMENT;
    properties.add(property1);
    properties.add(property2);
    ColumnSpec spec = new ColumnSpec(dataType, isRequired, dataTypeFormat, 2, properties);
    Mockito.when(column.getSpec()).thenReturn(spec);
    return column;
  }

  @SuppressWarnings("unchecked")
  private Table<TestObject> createTable(Column col1) {
    List<Column> columnList = Lists.of(col1);
    TableInfo metaData = mock(TableInfo.class);
    Table<TestObject> table = mock(Table.class);
    Mockito.when(table.getTableInfo()).thenReturn(metaData);
    Mockito.when(table.getColumns()).thenReturn(columnList);
    return table;
  }

  @Test
  public final void testValidate_WithException() throws Exception {
    Column col1 = createColumn(ColumnDataType.INTEGER, true);
    Table<TestObject> table = createTable(col1);

    SqliteTableSchemaValidator validator = new SqliteTableSchemaValidator();
    try {
      validator.validate(table);
      fail("Exception thrown for mismatch of column and format");
    } catch (ValidationException e) {
      assertEquals(e.getMessage(), "Column with name null is improperly defined. The data type for the "
          + "column is INTEGER which does not support Data Type ENUM_AS_INT");
    } catch (Exception e) {
      throw e;
    }
  }

  @Test
  public final void testValidate_PrimaryKeyException() throws Exception {
    Column col1 = createColumn(ColumnDataType.ENUM, false);
    Table<TestObject> table = createTable(col1);

    SqliteTableSchemaValidator validator = new SqliteTableSchemaValidator();

    try {
      validator.validate(table);
      fail("Exception should be thrown for Column part of Primary Key but not marked as required");
    } catch (ValidationException e) {
      assertEquals(e.getMessage(), "Column with name null" + " is part of the Primary key but not marked as Required."
          + " All Primary Key Columns must be marked as required.");
    } catch (Exception e) {
      throw e;
    }
  }
}
