package com.sarality.app.datastore.contact;

import com.sarality.app.data.DataObject;

/**
 * Simple Data for a contact
 * 
 * @author sunayna(Sunayna Uberoy)
 * 
 */
public class Contact implements DataObject<Contact> {

  // Id of the Contact
  private final Long contactId;

  // Name of the contact
  private final String name;

  // Confirms if the contact has a phone number
  private final Integer hasPhoneNumber;

  // contact's photo
  private final Integer photoId;

  public Contact(Long contactId, String name, Integer hasPhoneNumber, Integer photoId) {
    super();
    this.contactId = contactId;
    this.name = name;
    this.hasPhoneNumber = hasPhoneNumber;
    this.photoId = photoId;
  }

  @Override
  public ContactBuilder getBuilder() {
    return new ContactBuilder().setContactId(contactId).setName(name).setHasPhoneNumber(hasPhoneNumber)
        .setPhotoId(photoId);
  }

  @Override
  public ContactBuilder newBuilder() {
    return new ContactBuilder();
  }

  @Override
  public String toString() {
    return new StringBuilder().append("Contact Id : ").append(contactId).append(",\n").append("Name : ").append(name)
        .append(",\n").append("HAS_PHONE_NUMBER : ").append(hasPhoneNumber).append(",\n").append("PhotoID : ")
        .append(photoId).append("\n").toString();
  }

  public Long getContactId() {
    return contactId;
  }

  public String getName() {
    return name;
  }

  public Integer hasPhoneNumber() {
    return hasPhoneNumber;
  }

  public Integer getPhotoId() {
    return photoId;
  }
}
