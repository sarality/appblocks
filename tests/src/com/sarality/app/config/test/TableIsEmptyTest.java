package com.sarality.app.config.test;

import android.test.ActivityUnitTestCase;

import com.dothat.app.assistant.AssistantApp;
import com.dothat.app.common.data.ServiceProvider;
import com.dothat.app.common.data.ServiceType;
import com.dothat.app.module.reminder.ReminderListActivity;
import com.dothat.app.module.reminder.data.ActivityType;
import com.dothat.app.module.reminder.data.ReminderData;
import com.dothat.app.module.reminder.data.ReminderType;
import com.dothat.app.module.reminder.db.RemindersTable;
import com.sarality.app.config.TableIsEmpty;
import com.sarality.app.datastore.db.Table;

/**
 * Tests for {@link TableIsEmpty}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class TableIsEmptyTest extends ActivityUnitTestCase<ReminderListActivity> {

  public TableIsEmptyTest() {
    super(ReminderListActivity.class);
  }

  public void testConstructor() {
    RemindersTable reminderTable = null;
    TableIsEmpty emptyTable = new TableIsEmpty(reminderTable);
    assertNotNull(emptyTable);
  }

  public void testGetValue() {
    AssistantApp app = new AssistantApp(getInstrumentation().getTargetContext().getApplicationContext());

    Table<ReminderData> table = new RemindersTable(app);
    TableIsEmpty emptyTable = new TableIsEmpty(table);
    assertSame(true, emptyTable.getValue());

    // Making sure that the table is not empty
    table.open();
    ReminderData reminderData = new ReminderData.Builder().setReminderType(ReminderType.PAY_BILL)
        .setActivityType(ActivityType.BILL).setServiceType(ServiceType.WATER).setServiceProvider(ServiceProvider.BSES)
        .build();
    table.create(reminderData);
    table.close();

    assertSame(false, emptyTable.getValue());
  }

  public void testIsEditable() {
    RemindersTable table = null;
    TableIsEmpty emptyTable = new TableIsEmpty(table);
    assertSame(false, emptyTable.isEditable());
  }
}
