package com.sarality.app.datastore.db.test;

import java.util.List;

import android.app.Application;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.db.SqliteTable;
import com.sarality.app.datastore.db.SqliteTableSchemaUpdater;
import com.sarality.app.datastore.extractor.CursorDataExtractor;
import com.sarality.app.datastore.populator.ContentValuesPopulator;

class TestTable extends SqliteTable<TestObject> {
  protected TestTable(Application application, String dbName, String tableName, int tableVersion,
      List<Column> columnList, CursorDataExtractor<TestObject> extractor,
      ContentValuesPopulator<TestObject> populator, SqliteTableSchemaUpdater schemaUpdter) {
    super(application, dbName, tableName, tableVersion, columnList, extractor, populator, schemaUpdter);
  }
}
