package com.sarality.app.datastore.contact;

/**
 * Class to store the propoerties of a whatsapp number
 *
 * @author sunayna(Sunayna Uberoy)
 */
public class WhatsAppNumber {

  // The number by itself
  private final String number;

  // The Data Id
  private final Long dataId;

  // Constructor
  public WhatsAppNumber(String number, Long dataId) {
    this.number = number;
    this.dataId = dataId;
  }

  /**
   * Returns the whatsappNumber
   *
   * @return whatsappNumber
   */
  public String getNumber() {
    return number;
  }

  /**
   * Returns the data Id
   *
   * @return dataId
   */
  public Long getDataId() {
    return dataId;
  }
}
