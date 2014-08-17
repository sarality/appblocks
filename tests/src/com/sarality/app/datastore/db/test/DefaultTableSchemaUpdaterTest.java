package com.sarality.app.datastore.db.test;

import java.util.Arrays;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.db.DefaultTableSchemaUpdater;
import com.sarality.app.datastore.db.TableColumnProperty;

/**
 * Tests for {@link DefaultTableSchemaUpdater}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
@RunWith(RobolectricTestRunner.class)
public class DefaultTableSchemaUpdaterTest extends TestCase {

  public static final String TABLE_NAME = "test";
  private static final String DATABASE_NAME = "test.db";
  private static final int TABLE_VERSION = 1;

  @Test
  public void testDefaultTableSchemaUpdater() {
    DefaultTableSchemaUpdater schemaUpdater = new DefaultTableSchemaUpdater();
    assertNotNull(schemaUpdater);
  }

  private Column createColumn() {
    ColumnSpec spec;
    String name;
    spec = new ColumnSpec(ColumnDataType.INTEGER, true, TableColumnProperty.PRIMARY_KEY,
        TableColumnProperty.AUTO_INCREMENT);
    name = "Primary_Key";
    Column column = new TestColumn(name, spec);
    return column;
  }

  @Test
  public void testUpdateSchema() {
    DefaultTableSchemaUpdater schemaUpdater = new DefaultTableSchemaUpdater();
    Application app = Robolectric.application;
    Context context = app.getApplicationContext();
   
    TestTable testTable = new TestTable(app, DATABASE_NAME, TABLE_NAME, TABLE_VERSION, Arrays.asList(createColumn()),
        null, null, schemaUpdater);

    TestOnlineHelper onlineHelper = new TestOnlineHelper(context, null, null, TABLE_VERSION);

    SQLiteDatabase db = onlineHelper.getWritableDatabase();
    schemaUpdater.updateSchema(db, testTable);
    assertEquals(true, TableExists(db));
  }

  public boolean TableExists(SQLiteDatabase db) {
    Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[] {
        "table", TABLE_NAME });
    if (!cursor.moveToFirst()) {
      return false;
    }
    int count = cursor.getInt(0);
    cursor.close();
    return count > 0;
  }
}

class TestOnlineHelper extends SQLiteOpenHelper {

  public TestOnlineHelper(Context context, String name, CursorFactory factory, int version) {
    super(context, name, factory, version);
  }

  @Override
  public void onCreate(SQLiteDatabase arg0) {
  }

  @Override
  public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
  }
}
