package com.sarality.app.data;

/**
 * Base implementation that all EnumData implements must extend.
 * <p>
 * Also provides lookup for EnumData by class and name just like regular java enums. 
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> Type of EnumData
 */
public abstract class BaseEnumData<T extends EnumData<T>> implements EnumData<T> {

  // Global registry for all Enums in the system
  private static final EnumDataRegistry registry = new EnumDataRegistry();

  // Unique name for the enum - name is unique with the class
  private final String enumName;
  
  /**
   * Constructor.
   * 
   * @param enumName Unique string name for the EnumData
   */
  public BaseEnumData(String enumName) {
    this.enumName = enumName;
  }

  /**
   * Registers a given enum with the Global registry.
   * <p>
   * Overwrites the data that currently exists for the Class, name pair.
   * 
   * @param enumClass Class of the EnumData.
   * @param dataEnum data for the EnumData.
   */
  protected void register(Class<T> enumClass, T dataEnum) {
    registry.register(enumClass, dataEnum);
  }

  @Override
  public String getEnumName() {
    return enumName;
  }

  /**
   * Retrieve the EnumData instance for the given Class and name.
   * 
   * @param enumClass Class of EnumData to be retrieved.
   * @param name String name for the EnumData.
   * @return EnumData instance with given Class and name.
   */
  public static <T extends EnumData<T>> T valueOf(Class<T> enumClass, String name) {
    return EnumDataRegistry.valueOf(enumClass, name);
  }
}
