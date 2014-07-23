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

  // Unique name for the enum - name is unique with the class
  private final String enumName;
  private final EnumDataRegistry registry;

  /**
   * Constructor.
   * 
   * @param enumName Unique string name for the EnumData
   */
  public BaseEnumData(String enumName) {
    this(enumName, EnumDataRegistry.getGlobalInstance());
  }

  /**
   * Constructor for testing.
   * 
   * @param enumName Unique string name for the EnumData
   * @param registry Registry to use for testing purposes. By default a global registry is used.
   */
  public BaseEnumData(String enumName, EnumDataRegistry registry) {
    this.enumName = enumName;
    this.registry = registry;
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
    return EnumDataRegistry.getGlobalInstance().valueOf(enumClass, name);
  }
}
