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

  private final ContactLabel label;

  // Constructor
  public ContactNumber(String number, boolean isPrimary, ContactLabel label) {
    this.number = number;
    this.isPrimary = isPrimary;
    this.label = label;
  }

  public static enum ContactLabel implements MappedEnum<Integer> {
    HOME(1),
    MOBILE(2),
    WORK(2),
    OTHER(7),
    MAIN(12);
    

    private int mappedValue;

    private ContactLabel(int mappedValue) {
      this.mappedValue = mappedValue;
    }

    @Override
    public Integer getMappedValue() {
      return mappedValue;
    }

    @Override
    public String getMappedValueString() {
      return String.valueOf(mappedValue);
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
  public ContactLabel getLabel() {
    return label;
  }
}
