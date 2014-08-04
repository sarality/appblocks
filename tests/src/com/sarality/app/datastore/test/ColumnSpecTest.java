package com.sarality.app.datastore.test;

import static org.mockito.Mockito.mock;

import java.util.HashSet;
import java.util.Set;

import com.sarality.app.datastore.BaseFieldColumnConfigProvider;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnFormat;
import com.sarality.app.datastore.ColumnProperty;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.view.action.test.BaseUnitTest;

/**
 * Tests for {@link BaseFieldColumnConfigProvider}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ColumnSpecTest extends BaseUnitTest {

  ColumnDataType dataType = ColumnDataType.INTEGER;
  ColumnFormat format = ColumnFormat.ENUM_AS_INT;
  ColumnProperty property1 = mock(ColumnProperty.class);
  ColumnProperty property2 = mock(ColumnProperty.class);
  Set<ColumnProperty> propertySet = new HashSet<ColumnProperty>();
  
  public void setUp(){
    super.setUp();
    propertySet.add(property1);
    propertySet.add(property2);
  }

  private void checkTests(ColumnSpec spec){
    assertNotNull(spec);
    assertEquals(dataType, spec.getDataType());
    assertEquals(true, spec.isRequired());
    assertEquals(propertySet, spec.getProperties());
  }
  
  public final void testColumnSpec_DataTypeReqFormatSizePropertySet() {
    ColumnSpec spec = new ColumnSpec(dataType, true, format, 2, propertySet);

    checkTests(spec);
    assertEquals(format, spec.getFormat());
    assertEquals(2, spec.getSize());
  }

  public final void testColumnConstructorWithProperties() {
    
    ColumnSpec spec = new ColumnSpec(dataType, true, property1, property2);

    checkTests(spec);
    assertNull(spec.getFormat());
    assertEquals(-1, spec.getSize());
  }

  public final void testColumnSpecConstructorWithFormatProperties() {
    ColumnSpec spec = new ColumnSpec(dataType, format, true, property1, property2);
  
    checkTests(spec);
    assertEquals(format, spec.getFormat());
    assertEquals(-1, spec.getSize());
  }

  public final void testColumnSpecDataTypeSizeReqProperty() {
    ColumnSpec spec = new ColumnSpec(dataType, 1, true, property1, property2);
  
    checkTests(spec);
    assertNull(spec.getFormat());
    assertEquals(1, spec.getSize());
  }

  public final void testColumnSpec_DataTypeFormatSizeReqProperty() {
    ColumnSpec spec = new ColumnSpec(dataType, format, 1, true, property1, property2);

    checkTests(spec);
    assertEquals(format, spec.getFormat());
    assertEquals(1, spec.getSize());
  }
}
