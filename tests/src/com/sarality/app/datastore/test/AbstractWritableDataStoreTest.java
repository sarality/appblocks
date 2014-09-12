package com.sarality.app.datastore.test;

import static org.mockito.Mockito.mock;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.datastore.AbstractWritableDataStore;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ContentValuesPopulator;
import com.sarality.app.datastore.CursorDataExtractor;
import com.sarality.app.datastore.db.test.TestObject;
import com.sarality.app.datastore.query.Query;

/**
 * Tests for {@link AbstractWritableDataStore}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
@RunWith(RobolectricTestRunner.class)
public class AbstractWritableDataStoreTest extends TestCase {

  @Test
  @SuppressWarnings("unchecked")
  public void testAbstractWritableDataStore() {
    List<Column> columnList = Lists.of();
    String storeName = "Test Store";
    CursorDataExtractor<TestObject> extractor = mock(CursorDataExtractor.class);
    ContentValuesPopulator<TestObject> populator = mock(ContentValuesPopulator.class);
    TestStore store = new TestStore(storeName, columnList, extractor,populator);
    assertEquals(0, store.getColumns().size());
    assertEquals(extractor, store.getCursorDataExtractor());
    assertEquals(storeName, store.getName());
    assertEquals(populator, store.getContentValuesPopulator());
  }


  class TestStore extends AbstractWritableDataStore<TestObject, String> {

    public TestStore(String name, List<Column> columnList, CursorDataExtractor<TestObject> extractor,
        ContentValuesPopulator<TestObject> populator) {
      super(name, columnList, extractor, populator);
    }

    @Override
    public String create(TestObject data) {
      return null;
    }

    @Override
    public void delete(Query query) {
    }

    @Override
    public void update(TestObject data, Query query) {
    }

    @Override
    public List<TestObject> query(Query query) {
      return null;
    }

  }
}
