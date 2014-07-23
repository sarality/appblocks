package com.sarality.app.datastore.sms.test;

import java.util.List;

import android.test.ActivityUnitTestCase;
import android.test.MoreAsserts;

import com.dothat.app.module.reminder.ReminderListActivity;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.sms.SmsDataStore;
import com.sarality.app.datastore.sms.SmsMessage;

/**
 * Tests for {@link SmsDataStore}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class SmsDataStoreTest extends ActivityUnitTestCase<ReminderListActivity> {

  public SmsDataStoreTest() {
    super(ReminderListActivity.class);
  }
  
  public void testSmsDataStore() {
    SmsDataStore store = new SmsDataStore(getInstrumentation().getTargetContext());
    store.open();
    store.query(null);
    store.close();
  }

  public void testGetColumns(){
    SmsDataStore store = new SmsDataStore(getInstrumentation().getTargetContext());
    List<Column> list = store.getColumns();
    MoreAsserts.assertEquals(list.toArray(), SmsDataStore.Column.values());
  }
  
  public void testQuery(){
    SmsDataStore store = new SmsDataStore(getInstrumentation().getTargetContext());
    store.open();
    List<SmsMessage> returnedDataList = store.query(null);
    store.close();
    assertNotNull(returnedDataList);
  }

}
