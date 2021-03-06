package com.sarality.app.common.collect.test;

import java.util.Map;

import junit.framework.TestCase;

import com.sarality.app.common.collect.Maps;
import com.sarality.app.common.collect.Maps.Builder;
import com.sarality.app.view.action.test.MoreAsserts;

/**
 * Tests for {@link Maps}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class MapsTest extends TestCase {

  public void testEmpty() {
    Map<String, Long> map = Maps.empty();
    assertNotNull(map);
  }

  public void testBuilderObject_Instance() {
    Builder<String, Long> builder = Maps.builder();
    assertNotNull(builder);
  }

  public void testBuilderClass_Build() {
    Builder<String, Long> builder = Maps.builder();
    Long longValue = Long.valueOf("1234567890");
    Map<String, Long> map = builder.with("test", longValue).build();
    assertNotNull(map);
    MoreAsserts.assertNotEmpty(map);
    assertNotNull(map.containsKey("test"));
    assertEquals(longValue, map.get("test"));
  }
}
