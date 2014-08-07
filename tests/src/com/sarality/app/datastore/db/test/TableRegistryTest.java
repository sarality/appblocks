package com.sarality.app.datastore.db.test;

import static org.mockito.Mockito.mock;

import java.util.Arrays;

import org.mockito.Mockito;

import android.app.Application;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.db.TableColumnProperty;
import com.sarality.app.datastore.db.TableRegistry;
import com.sarality.app.view.action.test.BaseUnitTest;

/**
 * Tests for {@link TableRegistry}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class TableRegistryTest extends BaseUnitTest {

  private Column createColumn() {
    Column column = mock(Column.class);
    ColumnSpec spec;
    String name;
    spec = new ColumnSpec(ColumnDataType.INTEGER, true, TableColumnProperty.PRIMARY_KEY,
        TableColumnProperty.AUTO_INCREMENT);
    name = "Primary_Key";
    Mockito.when(column.getSpec()).thenReturn(spec);
    Mockito.when(column.getName()).thenReturn(name);
    return column;
  }
  
  public final void testRegister() {
    TableRegistry register = new TableRegistry();
    Application app = mock(Application.class);
    Mockito.when(app.getApplicationContext()).thenReturn(context);
    
    TestTable table = new TestTable(app, "TestDb", "TestTable", 1, Arrays.asList(createColumn()), null, null, null);

    register.register(table);
    assertEquals(table,register.getTable("TestTable"));
  }
  
  public final void testRegister_WithNullValue() {
    TableRegistry register = new TableRegistry();
    assertNull(register.getTable("TestTable"));
  }
}
