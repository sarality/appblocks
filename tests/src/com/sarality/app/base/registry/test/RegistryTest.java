package com.sarality.app.base.registry.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.sarality.app.base.registry.Registry;
import com.sarality.app.base.registry.Registry.Entry;
import com.sarality.app.base.registry.Registry.EntryProvider;

public class RegistryTest extends TestCase {

	TestDummyRegistry registry;
	
	public RegistryTest() {
		super();
	}
	
	@Override
	public void setUp(){
		registry = new TestDummyRegistry();
		assertNotNull(registry);
	}

	@Override
	public void tearDown(){
	}
	
	public void test01() {
		Entry<String, String> entry = new Entry<String, String>("Name",
				"Registry");
		List<Entry<String, String>> list = new ArrayList<Entry<String, String>>();
		list.add(entry);
		registry.register(list);
		assertEquals(registry.lookup("Name"), "Registry");
	}

	public void test02() {
		TestDummyRegistryEntryProvider entry = new TestDummyRegistryEntryProvider();
		entry.addEntry();
		registry.register(entry);
		assertEquals(registry.lookup("Key"), "Value");
	}

	public void test03() {
		TestDummyRegistryEntryProvider entry = new TestDummyRegistryEntryProvider();
		entry.addEntry();
		assertNotNull(entry.provide());
	}
	
	public void test04() {
		Entry<String, String> entry = new Entry<String, String>("Name",
				"Registry");
		assertEquals(entry.getKey(),"Name");
		assertEquals(entry.getValue(),"Registry");
	}
	
	private class TestDummyRegistry extends Registry<String, String> {
		public TestDummyRegistry() {
			super();
		}
	}

	private class TestDummyRegistryEntryProvider extends
			EntryProvider<String, String> {
		public TestDummyRegistryEntryProvider() {
			super();
		}

		public void addEntry() {
			addEntry("Key", "Value");
		}
	}
}
