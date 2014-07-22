package com.sarality.app.common.collect.test;

import java.util.List;

import junit.framework.TestCase;

import com.sarality.app.common.collect.Lists;

/**
 * Tests for {@link Lists}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ListsTest extends TestCase {

  public void testNullEntry() {
    List<String> list = Lists.of();
    assertNotNull(list);
  }

  public void testMultipleEntry() {
    List<Integer> list = Lists.of(1, 2, 3, 4, 5);
    assertNotNull(list);
    assertEquals(5, list.size());
    assertSame(2, list.get(1));
  }
}
