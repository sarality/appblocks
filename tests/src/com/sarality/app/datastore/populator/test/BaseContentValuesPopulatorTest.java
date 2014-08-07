package com.sarality.app.datastore.populator.test;

import junit.framework.TestCase;
import android.content.ContentValues;

import com.sarality.app.datastore.db.DefaultTableSchemaUpdater;
import com.sarality.app.datastore.populator.BaseContentValuesPopulator;
import com.sarality.app.datastore.populator.DateValuePopulator;
import com.sarality.app.datastore.populator.EnumValuePopulator;
import com.sarality.app.datastore.populator.LongValuePopulator;
import com.sarality.app.datastore.populator.StringValuePopulator;

/**
 * Tests for {@link DefaultTableSchemaUpdater}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class BaseContentValuesPopulatorTest extends TestCase {

  public final void testGetLongValuePopulator() {
    TestPopulator populator = new TestPopulator();
    assertNotNull(populator);
    assertEquals(LongValuePopulator.class, populator.getLongValuePopulator().getClass());
    assertEquals(DateValuePopulator.class, populator.getDateValuePopulator().getClass());
    assertEquals(EnumValuePopulator.class, populator.getEnumValuePopulator().getClass());
    assertEquals(StringValuePopulator.class, populator.getStringValuePopulator().getClass());
  }

  class TestPopulator extends BaseContentValuesPopulator<Long> {
    @Override
    public void populate(ContentValues contentValues, Long data) {
    }
  }
}
