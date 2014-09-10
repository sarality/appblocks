package com.sarality.app.datastore.db.test;

import static org.mockito.Mockito.mock;

import java.util.List;

import junit.framework.TestCase;

import org.fest.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;

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
import com.sarality.app.datastore.query.Query;

/**
 * Tests for {@link SqliteTable}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
@RunWith(RobolectricTestRunner.class)
@Config(shadows = { ShadowSQLiteOpenHelper.class })
public class SqliteTableTest extends TestCase {

  private List<Column> colList;
  private ContentValuesPopulator<TestObject> populator;
  private CursorDataExtractor<TestObject> extractor;
  private final String primaryColName = "PrimaryCol";
  private final String textColName = "TextCol";

  @Before
  public void setUp() {
    colList = createColumnList();
  }

  private List<Column> createColumnList() {
    ColumnSpec spec = new ColumnSpec(ColumnDataType.INTEGER, true, TableColumnProperty.PRIMARY_KEY,
        TableColumnProperty.AUTO_INCREMENT);
    Column primaryCol = new TestColumn(primaryColName, spec);

    spec = new ColumnSpec(ColumnDataType.TEXT, false);
    Column textCol = new TestColumn(textColName, spec);

    List<Column> colList = Lists.of(primaryCol, textCol);
    return colList;
  }

  private TestTable createTable() {
    Application app = Robolectric.application;
    extractor = new TestExtractor();
    populator = new TestPopulator();
    SqliteTableSchemaUpdater updater = mock(SqliteTableSchemaUpdater.class);
    TestTable table = new TestTable(app, "TestDb", "TestTable", 1, colList, extractor, populator, updater);
    return table;
  }

  @Test
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

  @Test
  public final void testCreateWithoutOpening() {
    TestTable table = createTable();
    TestObject data = mock(TestObject.class);
    try {
      table.create(data);
      fail("IllegalState Exception not thrown");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Cannot perform operation since the database was either not opened or "
          + "has already been closed.");
    }
  }

  @Test
  public final void testCreate_WithNullValue() {
    TestTable table = createTable();
    TestObject data = mock(TestObject.class);
    table.open();
    assertEquals(true, table.create(data) < 0);
    table.close();
  }

  private TestTable createTwoEntries() {
    TestObject data = new TestObject.Builder().setText("Testing1").build();
    TestTable table = createTable();
    table.open();
    table.create(data);

    data = new TestObject.Builder().setText("Testing2").build();
    table.create(data);
    return table;
  }

  @Test
  public final void testDelete() {
    Query query = mock(Query.class);
    TestTable table = createTwoEntries();
    Mockito.when(query.getWhereClause()).thenReturn(textColName + "=?");
    Mockito.when(query.getWhereClauseValues()).thenReturn(Arrays.array("Testing1"));
    table.delete(query);
    List<TestObject> list = table.query(null);
    assertEquals(1, list.size());
    assertEquals("Testing2", list.get(0).getValue());
    table.close();
  }

  @Test
  public final void testQuery() {
    TestTable table = createTwoEntries();
    List<TestObject> list = table.query(null);
    assertEquals(2, list.size());
    assertEquals("Testing2", list.get(1).getValue());
    assertEquals("Testing1", list.get(0).getValue());
    table.close();
  }

  public final void testUpdate() {
    TestTable table = createTwoEntries();
    Query query = mock(Query.class);
    Mockito.when(query.getWhereClause()).thenReturn(textColName + "=?");
    Mockito.when(query.getWhereClauseValues()).thenReturn(Arrays.array("Testing1"));
    TestObject data = new TestObject.Builder().setText("Testing3").build();
    table.update(data, query);
    List<TestObject> list = table.query(null);
    assertEquals(2, list.size());
    assertEquals("Testing3", list.get(1).getValue());
    assertEquals("Testing1", list.get(0).getValue());
    table.close();
  }

  class TestExtractor implements CursorDataExtractor<TestObject> {

    public TestObject extract(Cursor cursor, Query query) {
      TestObject.Builder builder = new TestObject.Builder();
      TestObject data = builder.setId(cursor.getInt(cursor.getColumnIndex(primaryColName)))
          .setText(cursor.getString(cursor.getColumnIndex(textColName))).build();
      return data;
    }
  }

  class TestPopulator implements ContentValuesPopulator<TestObject> {

    public boolean populate(ContentValues contentValues, TestObject data) {
      if (data.getValue() != null) {
        contentValues.put("TextCol", data.getValue());
        return true;
      }
      return false;
    }
  }
}
