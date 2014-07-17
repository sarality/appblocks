package com.sarality.app.base.registry.test;

import java.util.List;

import junit.framework.TestCase;

import com.sarality.app.base.registry.MultiValueRegistry;

public class MultiValueRegistryTest extends TestCase {

  MultiValueRegistry<String, String> multiValueRegistry;

  public MultiValueRegistryTest(String name) {
    super(name);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    multiValueRegistry = new MultiValueRegistry<String, String>();
    assertNotNull(multiValueRegistry);
  }

  public final void testLookup_SingleValue() {
    TestDummyEntryProvider entryProvider = new TestDummyEntryProvider();
    entryProvider.addEntry("Key", "Value");
    multiValueRegistry.register(entryProvider);
    List<String> list = multiValueRegistry.lookup("Key");
    assertNotNull(list);
    assertEquals(1, list.size());
  }

  public final void testLookup_MultiValue() {
    TestDummyEntryProvider entryProvider = new TestDummyEntryProvider();
    entryProvider.addEntry("Key", "Value1");
    entryProvider.addEntry("Key", "Value2");
    entryProvider.addEntry("Key", "Value3");
    multiValueRegistry.register(entryProvider);
    List<String> list = multiValueRegistry.lookup("Key");
    assertNotNull(list);
    assertEquals(3, list.size());
    assertTrue(multiValueRegistry.lookup("Key").contains("Value1"));
    assertTrue(multiValueRegistry.lookup("Key").contains("Value2"));
    assertTrue(multiValueRegistry.lookup("Key").contains("Value3"));
  }

  private class TestDummyEntryProvider extends MultiValueRegistry.EntryProvider<String, String> {
    public void addEntry(String k, String v) {
      super.addEntry(k, v);
    }
  }
}
