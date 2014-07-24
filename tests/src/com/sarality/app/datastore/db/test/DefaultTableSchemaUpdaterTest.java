package com.sarality.app.datastore.db.test;

import java.util.Arrays;
import java.util.List;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.dothat.app.assistant.AssistantApp;
import com.dothat.app.module.reminder.db.RemindersTable.Column;
import com.sarality.app.datastore.db.DefaultTableSchemaUpdater;
import com.sarality.app.datastore.db.SqliteTable;
import com.sarality.app.datastore.db.SqliteTableSchemaUpdater;
import com.sarality.app.datastore.extractor.CursorDataExtractor;
import com.sarality.app.datastore.populator.ContentValuesPopulator;
import com.sarality.app.datastore.sms.SmsMessage;
import com.sarality.app.view.action.test.BaseUnitTest;

/**
 * Tests for {@link DefaultTableSchemaUpdater}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class DefaultTableSchemaUpdaterTest extends BaseUnitTest {

  public static final String TABLE_NAME = "test";
  private static final String DATABASE_NAME = "test.db";
  private static final int TABLE_VERSION = 1;

  public void testDefaultTableSchemaUpdater() {
    DefaultTableSchemaUpdater schemaUpdater = new DefaultTableSchemaUpdater();
    assertNotNull(schemaUpdater);
  }

  public void testUpdateSchema() {
    DefaultTableSchemaUpdater schemaUpdater = new DefaultTableSchemaUpdater();
    AssistantApp app = new AssistantApp(getInstrumentation().getTargetContext().getApplicationContext());

    TestTable testTable = new TestTable(app, DATABASE_NAME, TABLE_NAME, TABLE_VERSION,
        Arrays.<com.sarality.app.datastore.Column> asList(Column.values()), null, null, schemaUpdater);

    TestOnlineHelper onlineHelper = new TestOnlineHelper(getInstrumentation().getTargetContext()
        .getApplicationContext(), null, null, TABLE_VERSION);

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

class TestTable extends SqliteTable<SmsMessage> {

  protected TestTable(Application application, String dbName, String tableName, int tableVersion,
      List<com.sarality.app.datastore.Column> columnList, CursorDataExtractor<SmsMessage> extractor,
      ContentValuesPopulator<SmsMessage> populator, SqliteTableSchemaUpdater schemaUpdter) {
    super(application, dbName, tableName, tableVersion, columnList, extractor, populator, schemaUpdter);
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