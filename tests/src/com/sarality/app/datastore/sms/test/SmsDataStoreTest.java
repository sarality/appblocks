package com.sarality.app.datastore.sms.test;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.sms.SmsDataStore;
import com.sarality.app.view.action.test.MoreAsserts;

/**
 * Tests for {@link SmsDataStore}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
@RunWith(RobolectricTestRunner.class)
public class SmsDataStoreTest extends TestCase {

  public void testSmsDataStore() {
   // TODO CREATE A NEW Store and then add query 
  }

  @Test
  public void testGetColumns() {
    SmsDataStore store = new SmsDataStore(Robolectric.application.getApplicationContext());
    List<Column> list = store.getColumns();
    MoreAsserts.assertEquals(list.toArray(), SmsDataStore.Column.values());
  }

}
