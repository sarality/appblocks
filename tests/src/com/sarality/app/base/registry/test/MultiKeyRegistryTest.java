package com.sarality.app.base.registry.test;

import junit.framework.TestCase;

import com.sarality.app.base.registry.MultiKeyRegistry;
import com.sarality.app.base.registry.MultiValueRegistry;

/**
 * Tests for {@link MultiValueRegistry}.
 * 
 * @author abhideep@ (Abhideep Singh)
 * @author sunayna@ (Sunayna Uberoy)
 */
public class MultiKeyRegistryTest extends TestCase {

  public void test_Constructor() {
    MultiKeyRegistry<Integer, String> registry = new MultiKeyRegistry<Integer, String>();
    assertNotNull(registry);
  }

  public void testRegister_SingleKey() {
    TestDummyEntryProvider entryProvider = new TestDummyEntryProvider();
    entryProvider.addEntry(1, "Test");
    MultiKeyRegistry<Integer, String> registry = new MultiKeyRegistry<Integer, String>();
    registry.register(entryProvider);
    assertNotNull(registry.lookup(1));
    assertEquals("Test", registry.lookup(1));
  }

  public void testRegister_MultiKey() {
    TestDummyEntryProvider entryProvider = new TestDummyEntryProvider();
    entryProvider.addEntry(1, "Test");
    entryProvider.addEntry(2, "Test");
    entryProvider.addEntry(3, "Test");
    entryProvider.addEntry(4, "Test");
    entryProvider.addEntry(5, "Test");
    MultiKeyRegistry<Integer, String> registry = new MultiKeyRegistry<Integer, String>();
    registry.register(entryProvider);
    assertEquals(5, entryProvider.provide().size());
    assertEquals(registry.lookup(1), registry.lookup(2));
  }

  private class TestDummyEntryProvider extends MultiKeyRegistry.EntryProvider<Integer, String> {
    public void addEntry(Integer k, String v) {
      super.addEntry(v, k);
    }
  }
}
