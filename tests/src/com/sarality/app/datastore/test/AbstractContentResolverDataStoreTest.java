package com.sarality.app.datastore.test;

import static org.mockito.Mockito.mock;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import android.content.ContentResolver;
import android.net.Uri;
import android.test.mock.MockContentResolver;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.datastore.AbstractContentResolverDataStore;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.CursorDataExtractor;
import com.sarality.app.datastore.db.test.TestObject;
import com.sarality.app.datastore.query.Query;

/**
 * Tests for {@link AbstractContentResolverDataStore}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
@RunWith(RobolectricTestRunner.class)
public class AbstractContentResolverDataStoreTest extends TestCase {

  @Test
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
