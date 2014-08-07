package com.sarality.app.datastore.test;

import static org.mockito.Mockito.mock;

import java.util.List;

import android.content.Context;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.datastore.AbstractWritableDataStore;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.extractor.CursorDataExtractor;
import com.sarality.app.datastore.populator.ContentValuesPopulator;
import com.sarality.app.datastore.query.Query;
import com.sarality.app.view.action.test.BaseUnitTest;

/**
 * Tests for {@link AbstractWritableDataStore}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class AbstractWritableDataStoreTest extends BaseUnitTest {

  @SuppressWarnings("unchecked")
  public void testAbstractWritableDataStore() {
    List<Column> columnList = Lists.of();
    String storeName = "Test Store";
    CursorDataExtractor<TestObject> extractor = mock(CursorDataExtractor.class);
    ContentValuesPopulator<TestObject> populator = mock(ContentValuesPopulator.class);
    TestStore store = new TestStore(context, storeName, columnList, extractor,populator);
    assertEquals(0, store.getColumns().size());
    assertEquals(extractor, store.getCursorDataExtractor());
    assertEquals(storeName, store.getName());
    assertEquals(context, store.getApplicationContext());
    assertEquals(populator, store.getContentValuesPopulator());
  }


  class TestStore extends AbstractWritableDataStore<TestObject, String> {

    public TestStore(Context context, String name, List<Column> columnList, CursorDataExtractor<TestObject> extractor,
        ContentValuesPopulator<TestObject> populator) {
      super(context, name, columnList, extractor, populator);
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
