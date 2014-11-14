package com.sarality.app.datastore.contact;

import com.sarality.app.datastore.MappedEnum;

/**
 * ContactNumber to store properties of a number
 * 
 * @author sunayna(Sunayna Uberoy)
 * 
 */
public class ContactNumber {

  // The number by itself
  private final String number;

  // If the number is private
  private final boolean isPrimary;

  private final LabelType label;

  // Constructor
  public ContactNumber(String number, boolean isPrimary, LabelType label) {
    this.number = number;
    this.isPrimary = isPrimary;
    this.label = label;
  }

  public static enum LabelType implements MappedEnum<Integer> {
    Home(1),
    Mobile(2),
    Work(2),
    Other(7),
    Main(12);
    

    private int mappedValue;

    private LabelType(int mappedValue) {
      this.mappedValue = mappedValue;
    }

    @Override
    public Integer getMappedValue() {
      return mappedValue;
    }
  }
  
  /**
   * Returns the phoneNumber
   * 
   * @return phoneNumber
   */
  public String getNumber() {
    return number;
  }

  /**
   * Returns if the number is the primary number
   * 
   * @return
   */
  public boolean isPrimary() {
    return isPrimary;
  }

  /**
   * Returns the label of the number
   * 
   * @return label
   */
  public LabelType getLabel() {
    return label;
  }
}
