package com.sarality.app.config.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.sarality.app.config.TableIsEmpty;
import com.sarality.app.data.BaseFieldBasedDataObject;
import com.sarality.app.datastore.db.Table;
import com.sarality.app.view.action.test.BaseUnitTest;

/**
 * Tests for {@link TableIsEmpty}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class TableIsEmptyTest extends BaseUnitTest {

  @SuppressWarnings("unchecked")
  private Table<TestDataObject> testTable = (Table<TestDataObject>) mock(Table.class);

  public void testConstructor() {
    TableIsEmpty emptyTable = new TableIsEmpty(testTable);
    assertNotNull(emptyTable);
  }

  public void testGetValue() {
    TableIsEmpty emptyTable = new TableIsEmpty(testTable);

    List<TestDataObject> dataList = new ArrayList<TestDataObject>();
    when(testTable.query(null)).thenReturn((List<TestDataObject>) dataList);

    // Check for Empty Table
    assertSame(true, emptyTable.getValue());

    // Add Entry into list and check for non emptytable
    TestDataObject data = new TestDataObject();
    dataList.add(data);
    assertSame(false, emptyTable.getValue());
  }

  public void testIsEditable() {
    TableIsEmpty emptyTable = new TableIsEmpty(testTable);
    assertSame(false, emptyTable.isEditable());
  }
}

class TestDataObject extends BaseFieldBasedDataObject<TestDataObject>{

  @Override
  public com.sarality.app.data.FieldBasedDataObject.Builder<TestDataObject> newBuilder() {
    return null;
  }
  
}