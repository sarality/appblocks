package com.sarality.app.base.registry.test;

import java.util.List;

import junit.framework.TestCase;
import android.test.MoreAsserts;

import com.sarality.app.base.registry.MultiValueRegistry;
import com.sarality.app.common.collect.Lists;

/**
 * Tests for {@link MultiValueRegistry}.
 * 
 * @author abhideep@ (Abhideep Singh)
 * @author sunayna@ (Sunayna Uberoy)
 */
public class MultiValueRegistryTest extends TestCase {

  private MultiValueRegistry<String, String> multiValueRegistry;

  public MultiValueRegistryTest(String name) {
    super(name);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    multiValueRegistry = new MultiValueRegistry<String, String>();
    assertNotNull(multiValueRegistry);
  }

  public final void testRegisterAndLookup_SingleValue() {
    TestDummyEntryProvider entryProvider = new TestDummyEntryProvider();
    entryProvider.addEntry("key1", "value11");
    entryProvider.addEntry("key2", "value21");
    multiValueRegistry.register(entryProvider);
    
    List<String> list = multiValueRegistry.lookup("key1");
    assertNotNull(list);
    assertEquals(1, list.size());
    assertEquals(list, Lists.of("value11"));
    
    list = multiValueRegistry.lookup("key2");
    assertNotNull(list);
    assertEquals(1, list.size());
    assertEquals(list, Lists.of("value21"));
  }

  public final void testRegisterAndLookup_MultipleValues() {
    TestDummyEntryProvider entryProvider = new TestDummyEntryProvider();
    entryProvider.addEntry("key1", "value11");
    entryProvider.addEntry("key1", "value12");
    entryProvider.addEntry("key1", "value13");
    
    entryProvider.addEntry("key2", "value21");
    entryProvider.addEntry("key2", "value22");
    multiValueRegistry.register(entryProvider);

    List<String> list = multiValueRegistry.lookup("key1");
    assertNotNull(list);
    assertEquals(3, list.size());
    MoreAsserts.assertContentsInAnyOrder(list, "value11", "value12", "value13");
    
    list = multiValueRegistry.lookup("key2");
    assertNotNull(list);
    assertEquals(2, list.size());
    MoreAsserts.assertContentsInAnyOrder(list, "value21", "value22");
  }

  private class TestDummyEntryProvider extends MultiValueRegistry.EntryProvider<String, String> {
    public void addEntry(String key, String value) {
      super.addEntry(key, value);
    }
  }
}
