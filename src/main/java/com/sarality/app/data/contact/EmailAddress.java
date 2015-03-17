package com.sarality.app.data.contact;

/**
 * Email address for a person or company.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class EmailAddress {

  private final String address;
  private final String label;

  public EmailAddress(String address, String label) {
    this.address = address;
    this.label = label;
  }

  public String getAddress() {
    return address;
  }

  public String getLabel() {
    return label;
  }
}
