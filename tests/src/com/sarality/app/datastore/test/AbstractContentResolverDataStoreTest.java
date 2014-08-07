package com.sarality.app.datastore.test;

import static org.mockito.Mockito.mock;

import java.util.List;

import junit.framework.TestCase;
import android.content.ContentResolver;
import android.net.Uri;
import android.test.mock.MockContentResolver;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.datastore.AbstractContentResolverDataStore;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.extractor.CursorDataExtractor;
import com.sarality.app.datastore.query.Query;

/**
 * Tests for {@link AbstractContentResolverDataStore}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class AbstractContentResolverDataStoreTest extends TestCase {

  @SuppressWarnings("unchecked")
  public void testAbstractContentResolverDataStore() {
    List<Column> columnList = Lists.of();
    String storeName = "Test Store";
    CursorDataExtractor<TestObject> extractor = mock(CursorDataExtractor.class);
    TestStore store = new TestStore(storeName, columnList, extractor);
    assertEquals(0, store.getColumns().size());
    assertEquals(extractor, store.getCursorDataExtractor());
    assertEquals(storeName, store.getName());
  }
}

class TestStore extends AbstractContentResolverDataStore<TestObject> {

  public TestStore(String name, List<Column> columnList, CursorDataExtractor<TestObject> extractor) {
    super(name, columnList, extractor);
  }

  @Override
  public Uri getQueryUri(Query query) {
    return null;
  }

  @Override
  public ContentResolver getContentResolver() {
    return new MockContentResolver();
  }
}
