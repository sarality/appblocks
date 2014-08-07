package com.sarality.app.datastore.test;

import static org.mockito.Mockito.mock;

import java.util.List;

import junit.framework.TestCase;

import com.sarality.app.data.Field;
import com.sarality.app.datastore.BaseFieldColumnConfigProvider;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.FieldColumnConfig;
import com.sarality.app.datastore.extractor.ColumnValueExtractor;
import com.sarality.app.datastore.populator.FieldDataValuePopulator;

/**
 * Tests for {@link BaseFieldColumnConfigProvider}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class BaseFieldColumnConfigProviderTest extends TestCase {

  public final void testProvide_NullEntry() {
    BaseFieldColumnConfigProvider provider = new BaseFieldColumnConfigProvider();
    assertNotNull(provider);
    assertEquals(0,provider.provide().size());
  }

  public final void testProvide() {
    TestProvider provider = new TestProvider();
    Field field = mock(Field.class);
    Column column = mock(Column.class);
    ColumnValueExtractor<?> extractor = mock(ColumnValueExtractor.class);
    FieldDataValuePopulator populator = mock(FieldDataValuePopulator.class);
    provider.addProvider(field, column, extractor, populator);
    
    List<FieldColumnConfig> providerList = provider.provide();
    assertEquals(1, providerList.size());
    assertEquals(column,providerList.get(0).getColumn());
    assertEquals(field,providerList.get(0).getField());
    assertEquals(extractor,providerList.get(0).getExtractor());
    assertEquals(populator,providerList.get(0).getPopulator());
  }

  class TestProvider extends BaseFieldColumnConfigProvider{
    public void addProvider(Field field, Column column, ColumnValueExtractor<?> extractor, FieldDataValuePopulator populator){
      addEntry(field, column, extractor, populator);
    }
  }
}
