package com.sarality.app.datastore.db.test;

import static org.mockito.Mockito.mock;

import java.util.List;

import org.mockito.Mockito;

import android.app.Application;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.db.SqliteTable;
import com.sarality.app.datastore.db.SqliteTableSchemaUpdater;
import com.sarality.app.datastore.db.TableColumnProperty;
import com.sarality.app.datastore.db.TableInfo;
import com.sarality.app.datastore.extractor.CursorDataExtractor;
import com.sarality.app.datastore.populator.ContentValuesPopulator;
import com.sarality.app.datastore.test.TestObject;
import com.sarality.app.view.action.test.BaseUnitTest;

/**
 * Tests for {@link SqliteTable}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class SqliteTableTest extends BaseUnitTest {

  List<Column> colList;
  ContentValuesPopulator<TestObject> populator;
  CursorDataExtractor<TestObject> extractor;

  public void setUp() {
    super.setUp();
    colList = createColumnList();
  }

  private List<Column> createColumnList() {
    Column primaryCol = mock(Column.class);
    ColumnSpec spec = new ColumnSpec(ColumnDataType.INTEGER, true, TableColumnProperty.PRIMARY_KEY,
        TableColumnProperty.AUTO_INCREMENT);
    Mockito.when(primaryCol.getSpec()).thenReturn(spec);
    Mockito.when(primaryCol.getName()).thenReturn("Primary Col");

    Column textCol = mock(Column.class);
    spec = new ColumnSpec(ColumnDataType.TEXT, false);
    Mockito.when(textCol.getSpec()).thenReturn(spec);
    Mockito.when(textCol.getName()).thenReturn("Text Col");

    List<Column> colList = Lists.of(primaryCol, textCol);
    return colList;
  }

  @SuppressWarnings("unchecked")
  private TestTable createTable() {
    Application app = mock(Application.class);
    Mockito.when(app.getApplicationContext()).thenReturn(context);
    extractor = mock(CursorDataExtractor.class);
    populator = mock(ContentValuesPopulator.class);
    SqliteTableSchemaUpdater updater = mock(SqliteTableSchemaUpdater.class);
    TestTable table = new TestTable(app, "TestDb", "TestTable", 1, colList, extractor, populator, updater);
    return table;
  }

  public final void testCreateTable() {
    TestTable table = createTable();
    assertNotNull(table);
    assertEquals("TestDb", table.getDatabaseName());
    assertEquals(colList, table.getColumns());
    assertEquals(populator, table.getContentValuesPopulator());
    assertEquals(extractor, table.getCursorDataExtractor());
    assertEquals("TestTable", table.getName());
    assertEquals(1, table.getTableVersion());
    assertEquals(TableInfo.class, table.getTableInfo().getClass());

    table.open();
    table.close();
  }

  public final void testCreateWithoutOpening() {
    TestTable table = createTable();
    TestObject data = mock(TestObject.class);
    try {
      table.create(data);
      fail("IllegalState Exception not thrown");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(),
          "Cannot perform operation since the database was either not opened or " +
          "has already been closed.");
    }
  }

  public final void testCreate_WithNullValue() {
    TestTable table = createTable();
    TestObject data = mock(TestObject.class);
    table.open();
    assertEquals(true, table.create(data) < 0);
    table.close();
  }
 
  public final void testDelete() {
    fail("Not yet implemented"); // TODO
  }

  public final void testQuery() {
    fail("Not yet implemented"); // TODO
  }

  public final void testSetListener() {
    fail("Not yet implemented"); // TODO
  }

  public final void testUpdate() {
    fail("Not yet implemented"); // TODO
  }
}
