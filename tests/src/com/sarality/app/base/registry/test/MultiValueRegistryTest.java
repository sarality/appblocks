package com.sarality.app.base.registry.test;

import junit.framework.TestCase;

import com.sarality.app.base.registry.MultiValueRegistry;


public class MultiValueRegistryTest extends TestCase {

	MultiValueRegistry<String, String> multiValueRegistry;
	
	public MultiValueRegistryTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		multiValueRegistry = new MultiValueRegistry<String, String>();
		assertNotNull(multiValueRegistry);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testRegisterEntryProviderOfKV() {
		TestDummyEntryProvider entryProvider = new TestDummyEntryProvider();
		entryProvider.addEntry("Key", "Value");
		multiValueRegistry.register(entryProvider);
        assertNotNull(multiValueRegistry.lookup("Key"));
	}

	public final void testRegisterListOfEntryOfKV() {
		TestDummyEntryProvider entryProvider = new TestDummyEntryProvider();
		entryProvider.addEntry("Key", "Value1");
		entryProvider.addEntry("Key", "Value2");
		entryProvider.addEntry("Key", "Value3");
		multiValueRegistry.register(entryProvider);
		assertTrue(multiValueRegistry.lookup("Key").contains("Value1"));
		assertTrue(multiValueRegistry.lookup("Key").contains("Value2"));
		assertTrue(multiValueRegistry.lookup("Key").contains("Value3"));
	}

	private class TestDummyEntryProvider extends MultiValueRegistry.EntryProvider<String,String >{
		public void addEntry(String k, String v){
			super.addEntry(k,v);
		}
	}
}
