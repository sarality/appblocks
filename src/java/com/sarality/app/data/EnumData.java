package com.sarality.app.data;

/**
 * Interface for all Enums whose data is a combination of statically defined data and data loaded from a file.
 * <p>
 * An enum defined in such a way allows us to ship an app with only a small set of frequently used data and then load
 * other data as needed.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public interface EnumData {

  /**
   * @return String name of Enum.
   */
  public String getEnumName();
}
