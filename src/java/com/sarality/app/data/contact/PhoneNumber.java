package com.sarality.app.data.contact;

/**
 * Phone Number for a person or business
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class PhoneNumber {

  private final String number;
  private final String label;

  public PhoneNumber(String number, String label) {
    this.number = number;
    this.label = label;
  }

  public String getNumber() {
    return number;
  }

  public String getLabel() {
    return label;
  }
}
