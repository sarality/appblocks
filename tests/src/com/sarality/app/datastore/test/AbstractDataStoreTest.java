package com.sarality.app.datastore.test;

import static org.mockito.Mockito.mock;

import java.util.List;

import junit.framework.TestCase;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.datastore.AbstractDataStore;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.extractor.CursorDataExtractor;
import com.sarality.app.datastore.query.Query;

/**
 * Tests for {@link AbstractDataStore}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class AbstractDataStoreTest extends TestCase {

  @SuppressWarnings("unchecked")
  public void testAbstractDataStore() {
    List<Column> columnList = Lists.of();
    String storeName = "Test Store";
    CursorDataExtractor<TestObject> extractor = mock(CursorDataExtractor.class);
    TestStore store = new TestStore(storeName, columnList, extractor);
    assertEquals(0, store.getColumns().size());
    assertEquals(extractor, store.getCursorDataExtractor());
    assertEquals(storeName, store.getName());
  }

  class TestStore extends AbstractDataStore<TestObject> {

    public TestStore(String name, List<Column> columnList, CursorDataExtractor<TestObject> extractor) {
      super(name, columnList, extractor);
    }

    @Override
    public List<TestObject> query(Query query) {
      return null;
    }
  }
}
