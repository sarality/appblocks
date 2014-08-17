package com.sarality.app.datastore.test;

import static org.mockito.Mockito.mock;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import com.sarality.app.datastore.BaseFieldColumnConfigProvider;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnFormat;
import com.sarality.app.datastore.ColumnProperty;
import com.sarality.app.datastore.ColumnSpec;

/**
 * Tests for {@link BaseFieldColumnConfigProvider}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
@RunWith(RobolectricTestRunner.class)
public class ColumnSpecTest extends TestCase {

  ColumnDataType dataType = ColumnDataType.INTEGER;
  ColumnFormat format = ColumnFormat.ENUM_AS_INT;
  ColumnProperty property1 = mock(ColumnProperty.class);
  ColumnProperty property2 = mock(ColumnProperty.class);
  Set<ColumnProperty> propertySet = new HashSet<ColumnProperty>();

  @Before
  public void setUp() {
    propertySet.add(property1);
    propertySet.add(property2);
  }

  private void checkTests(ColumnSpec spec) {
    assertNotNull(spec);
    assertEquals(dataType, spec.getDataType());
    assertEquals(true, spec.isRequired());
    assertEquals(propertySet, spec.getProperties());
  }

  @Test
  public final void testColumnSpec_DataTypeReqFormatSizePropertySet() {
    ColumnSpec spec = new ColumnSpec(dataType, true, format, 2, propertySet);

    checkTests(spec);
    assertEquals(format, spec.getFormat());
    assertEquals(2, spec.getSize());
  }

  @Test
  public final void testColumnConstructorWithProperties() {
    ColumnSpec spec = new ColumnSpec(dataType, true, property1, property2);

    checkTests(spec);
    assertNull(spec.getFormat());
    assertEquals(-1, spec.getSize());
  }

  @Test
  public final void testColumnSpecConstructorWithFormatProperties() {
    ColumnSpec spec = new ColumnSpec(dataType, format, true, property1, property2);

    checkTests(spec);
    assertEquals(format, spec.getFormat());
    assertEquals(-1, spec.getSize());
  }

  @Test
  public final void testColumnSpecDataTypeSizeReqProperty() {
    ColumnSpec spec = new ColumnSpec(dataType, 1, true, property1, property2);

    checkTests(spec);
    assertNull(spec.getFormat());
    assertEquals(1, spec.getSize());
  }

  @Test
  public final void testColumnSpec_DataTypeFormatSizeReqProperty() {
    ColumnSpec spec = new ColumnSpec(dataType, format, 1, true, property1, property2);

    checkTests(spec);
    assertEquals(format, spec.getFormat());
    assertEquals(1, spec.getSize());
  }
}
