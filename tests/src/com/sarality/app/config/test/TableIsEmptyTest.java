package com.sarality.app.config.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.dothat.app.module.reminder.data.ReminderData;
import com.sarality.app.config.TableIsEmpty;
import com.sarality.app.datastore.db.Table;
import com.sarality.app.view.action.test.BaseUnitTest;

/**
 * Tests for {@link TableIsEmpty}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class TableIsEmptyTest extends BaseUnitTest {

  @SuppressWarnings("unchecked")
  private Table<ReminderData> testTable = (Table<ReminderData>) mock(Table.class);

  public void testConstructor() {
    TableIsEmpty emptyTable = new TableIsEmpty(testTable);
    assertNotNull(emptyTable);
  }

  @SuppressWarnings("unchecked")
  public void testGetValue() {
    TableIsEmpty emptyTable = new TableIsEmpty(testTable);

    List<ReminderData> dataList = new ArrayList<ReminderData>();
    when(testTable.query(null)).thenReturn((List<ReminderData>) dataList);

    // Check for Empty Table
    assertSame(true, emptyTable.getValue());

    // Add Entry into list and check for non emptytable
    ReminderData data = mock(ReminderData.class);
    dataList.add(data);
    assertSame(false, emptyTable.getValue());
  }

  public void testIsEditable() {
    TableIsEmpty emptyTable = new TableIsEmpty(testTable);
    assertSame(false, emptyTable.isEditable());
  }
}
