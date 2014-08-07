package com.sarality.app.datastore.test;

import static org.mockito.Mockito.mock;

import java.util.List;

import android.content.Context;
import android.net.Uri;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.datastore.AbstractContentResolverDataStore;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.extractor.CursorDataExtractor;
import com.sarality.app.datastore.query.Query;
import com.sarality.app.view.action.test.BaseUnitTest;

/**
 * Tests for {@link AbstractContentResolverDataStore}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class AbstractContentResolverDataStoreTest extends BaseUnitTest {

  @SuppressWarnings("unchecked")
  public void testAbstractContentResolverDataStore() {
    List<Column> columnList = Lists.of();
    String storeName = "Test Store";
    CursorDataExtractor<TestObject> extractor = mock(CursorDataExtractor.class);
    TestStore store = new TestStore(context, storeName, columnList, extractor);
    assertEquals(0, store.getColumns().size());
    assertEquals(extractor, store.getCursorDataExtractor());
    assertEquals(storeName, store.getName());
    assertEquals(context, store.getApplicationContext());
  }
}

class TestStore extends AbstractContentResolverDataStore<TestObject> {

  public TestStore(Context context, String name, List<Column> columnList, CursorDataExtractor<TestObject> extractor) {
    super(context, name, columnList, extractor);
  }

  @Override
  public Uri getQueryUri(Query query) {
    return null;
  }
}
