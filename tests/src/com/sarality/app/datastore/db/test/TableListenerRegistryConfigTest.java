package com.sarality.app.datastore.db.test;

import static org.mockito.Mockito.mock;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import com.sarality.app.datastore.db.Table;
import com.sarality.app.datastore.db.TableListener;
import com.sarality.app.datastore.db.TableListenerRegistryConfig;

/**
 * Tests for {@link TableListenerRegistryConfig}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
@RunWith(RobolectricTestRunner.class)
public class TableListenerRegistryConfigTest extends TestCase {

  @Test
  @SuppressWarnings("unchecked")
  public final void testListener() {
    Table<TestObject> sourceTable = mock(Table.class);
    Mockito.when(sourceTable.getTableName()).thenReturn("SourceTable");
    
    TableListener<TestObject> listener = mock(TableListener.class);
    TableListenerRegistryConfig<TestObject> listenerRegistry = new TableListenerRegistryConfig<TestObject>();
    
    TestObject data = mock(TestObject.class);
    listenerRegistry.addListener(listener,"SourceTable");
    listenerRegistry.listener(data, sourceTable);
    Mockito.verify(listener,Mockito.times(1)).OnEntryUpdated(data);
  }
}
