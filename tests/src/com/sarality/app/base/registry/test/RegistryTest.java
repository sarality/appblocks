package com.sarality.app.base.registry.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.sarality.app.base.registry.Registry;
import com.sarality.app.base.registry.Registry.Entry;
import com.sarality.app.base.registry.Registry.EntryProvider;
import com.sarality.app.view.action.test.MoreAsserts;

/**
 * Tests for {@link Registry}.
 * 
 * @author abhideep@ (Abhideep Singh)
 * @author sunayna@ (Sunayna Uberoy)
 */
public class RegistryTest extends TestCase {
  private Registry<String, String> registry;

  public RegistryTest(String name) {
    super(name);
  }

  @Override
  public void setUp() {
    registry = new Registry<String, String>();
    assertNotNull(registry);
  }

  public void testRegisterAndLookup_RegisterUsingList() {
    List<Entry<String, String>> list = new ArrayList<Entry<String, String>>();
    list.add(createEntry("key1", "value1"));
    list.add(createEntry("key2", "value2"));
    registry.register(list);
    assertEquals(registry.lookup("key1"), "value1");
    assertEquals(registry.lookup("key2"), "value2");
    assertNull(registry.lookup("key3"));
  }

  public void testRegisterAndLookup_RegisterUsingEmptyList() {
    List<Entry<String, String>> list = new ArrayList<Entry<String, String>>();
    registry.register(list);
    assertNull(registry.lookup("key1"));
  }

  public void testRegisterAndLookup_RegisterUsingProvider() {
    TestDummyRegistryEntryProvider provider = new TestDummyRegistryEntryProvider();
    provider.addEntry("key1", "value1");
    provider.addEntry("key2", "value2");
    registry.register(provider);
    assertEquals(registry.lookup("key1"), "value1");
    assertEquals(registry.lookup("key2"), "value2");
    assertNull(registry.lookup("key3"));
  }

  public void testRegisterAndLookup_RegisterUsingEmptyProvider() {
    TestDummyRegistryEntryProvider provider = new TestDummyRegistryEntryProvider();
    registry.register(provider);
    assertNull(registry.lookup("key1"));
  }

  public void testRegistryEntry() {
    Entry<String, String> entry = new Entry<String, String>("key", "value");
    assertEquals(entry.getKey(), "key");
    assertEquals(entry.getValue(), "value");
  }

  public void testRegistryEntryProvider_EmptyProvide() {
    TestDummyRegistryEntryProvider provider = new TestDummyRegistryEntryProvider();
    List<Entry<String, String>> list = provider.provide();
    assertNotNull(list);
    assertTrue(list.isEmpty());
  }

  public void testRegistryEntryProvider_ProvideWithMultipleEntries() {
    TestDummyRegistryEntryProvider provider = new TestDummyRegistryEntryProvider();
    provider.addEntry("key1", "value1");
    provider.addEntry("key2", "value2");
    provider.addEntry("key3", "value3");
    List<Entry<String, String>> list = provider.provide();
    assertNotNull(list);
    assertFalse(list.isEmpty());
    assertEquals(3, list.size());
    MoreAsserts.assertContentsInAnyOrder(list, createEntry("key1", "value1"), createEntry("key2", "value2"),
        createEntry("key3", "value3"));
  }

  private Entry<String, String> createEntry(String key, String value) {
    return new Entry<String, String>(key, value);
  }

  private class TestDummyRegistryEntryProvider extends EntryProvider<String, String> {

    public void addEntry(String key, String value) {
      super.addEntry(key, value);
    }
  }
}
