package com.sarality.app.config.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.sarality.app.config.TableIsEmpty;
import com.sarality.app.data.DataObject;
import com.sarality.app.datastore.db.Table;

/**
 * Tests for {@link TableIsEmpty}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class TableIsEmptyTest extends TestCase {

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

class TestDataObject implements DataObject<TestDataObject>{

  @Override
  public DataObject.Builder<TestDataObject> newBuilder() {
    return null;
  }

  @Override
  public DataObject.Builder<TestDataObject> getBuilder() {
    return null;
  }
}
