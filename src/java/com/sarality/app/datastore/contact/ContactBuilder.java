package com.sarality.app.datastore.contact;

import com.sarality.app.data.DataObject;

/**
 * Builds the Contact Data
 * 
 * @author sunayna(Sunayna Uberoy)
 * 
 */
public class ContactBuilder implements DataObject.Builder<Contact> {

  // Id of the contact
  private Long contactId;

  // Name of the person
  private String name;

  // Does the contact have a phone number
  private Integer hasPhoneNumber;

  // Photo ID of the contact
  private Integer photoId;

  @Override
  public Contact build() {
    return new Contact(contactId, name, hasPhoneNumber, photoId);
  }

  public ContactBuilder setContactId(Long contactId) {
    this.contactId = contactId;
    return this;
  }

  public ContactBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public ContactBuilder setPhotoId(Integer photoId) {
    this.photoId = photoId;
    return this;
  }

  public ContactBuilder setHasPhoneNumber(Integer hasPhoneNumber) {
    this.hasPhoneNumber = hasPhoneNumber;
    return this;
  }

}
