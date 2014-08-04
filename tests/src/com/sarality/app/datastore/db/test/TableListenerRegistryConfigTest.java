package com.sarality.app.datastore.db.test;

import static org.mockito.Mockito.mock;

import org.mockito.Mockito;

import com.sarality.app.datastore.db.Table;
import com.sarality.app.datastore.db.TableListener;
import com.sarality.app.datastore.db.TableListenerRegistryConfig;
import com.sarality.app.datastore.test.TestObject;
import com.sarality.app.view.action.test.BaseUnitTest;

/**
 * Tests for {@link TableListenerRegistryConfig}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class TableListenerRegistryConfigTest extends BaseUnitTest {

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
