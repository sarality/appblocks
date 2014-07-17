package com.sarality.app.base.registry.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.sarality.app.base.registry.Registry;
import com.sarality.app.base.registry.Registry.Entry;
import com.sarality.app.base.registry.Registry.EntryProvider;

public class RegistryTest extends TestCase {
  Registry<String,String> registry;

  public RegistryTest() {
    super();
  }

  @Override
  public void setUp() {
    registry = new Registry<String,String>();
    assertNotNull(registry);
  }

  public void testLookup_List() {
    Entry<String, String> entry = new Entry<String, String>("Name", "Registry");
    List<Entry<String, String>> list = new ArrayList<Entry<String, String>>();
    list.add(entry);
    registry.register(list);
    assertEquals(registry.lookup("Name"), "Registry");
  }

  public void testLookup_EntryProvider() {
    TestDummyRegistryEntryProvider entry = new TestDummyRegistryEntryProvider();
    entry.addEntry();
    registry.register(entry);
    assertEquals(registry.lookup("Key"), "Value");
  }

  public void testEntryProvider_Provide() {
    TestDummyRegistryEntryProvider entry = new TestDummyRegistryEntryProvider();
    entry.addEntry();
    assertNotNull(entry.provide());
  }

  public void testRegistryEntry() {
    Entry<String, String> entry = new Entry<String, String>("Name", "Registry");
    assertEquals(entry.getKey(), "Name");
    assertEquals(entry.getValue(), "Registry");
  }

  private class TestDummyRegistryEntryProvider extends EntryProvider<String, String> {
    public TestDummyRegistryEntryProvider() {
      super();
    }

    public void addEntry() {
      addEntry("Key", "Value");
    }
  }
}
