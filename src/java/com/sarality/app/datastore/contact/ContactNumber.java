package com.sarality.app.datastore.contact;

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

  // Constructor
  public ContactNumber(String number, boolean isPrimary) {
    this.number = number;
    this.isPrimary = isPrimary;
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
}
