package com.sarality.app.datastore.db.test;

import static org.mockito.Mockito.mock;

import java.util.List;

import org.mockito.Mockito;

import android.app.Application;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.db.TableColumnProperty;
import com.sarality.app.datastore.db.TableInfo;
import com.sarality.app.view.action.test.BaseUnitTest;

/**
 * Tests for {@link TableInfo}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class TableInfoTest extends BaseUnitTest {
  private Application app;
  
  public void setUp(){
    super.setUp();
    app = mock(Application.class);
    Mockito.when(app.getApplicationContext()).thenReturn(context);
  }
  
  private ColumnSpec createPrimaryKey(){
    ColumnSpec spec = new ColumnSpec(ColumnDataType.INTEGER, true, TableColumnProperty.PRIMARY_KEY,
        TableColumnProperty.AUTO_INCREMENT);
    return spec;
  }
  
  public final void testTableInfo_OnePrimaryKey() {
    Column primaryCol = mock(Column.class);
    Mockito.when(primaryCol.getSpec()).thenReturn(createPrimaryKey());
    
    Column testCol = mock(Column.class);
    Mockito.when(testCol.getSpec()).thenReturn(new ColumnSpec(ColumnDataType.TEXT, false));

    List<Column> colList = Lists.of(primaryCol, testCol);
    
    TestTable table = new TestTable(app, "TestDb", "TestTable", 1, colList, null, null, null);
    TableInfo info = table.getTableInfo();
    
    assertEquals(Lists.of(primaryCol), info.getPrimaryKeyColumns());
    assertFalse(info.hasCompositePrimaryKey());
  }
  
  public final void testTableInfo_TwoPrimaryKeys() {
    Column primaryCol = mock(Column.class);
    Mockito.when(primaryCol.getSpec()).thenReturn(createPrimaryKey());
    
    Column testCol = mock(Column.class);
    Mockito.when(testCol.getSpec()).thenReturn(createPrimaryKey());

    List<Column> colList = Lists.of(primaryCol, testCol);
    
    TestTable table = new TestTable(app, "TestDb", "TestTable", 1, colList, null, null, null);
    TableInfo info = table.getTableInfo();
    
    assertEquals(Lists.of(primaryCol, testCol), info.getPrimaryKeyColumns());
    assertTrue(info.hasCompositePrimaryKey());
  }
}
