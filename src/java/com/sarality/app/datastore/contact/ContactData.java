package com.sarality.app.datastore.contact;

import com.sarality.app.data.DataObject;

/**
 * Simple Data for a contact
 * 
 * @author sunayna(Sunayna Uberoy)
 * 
 */
public class ContactData implements DataObject<ContactData> {

  // Id of the Contact
  private final Long contactId;

  // Name of the contact
  private final String name;

  // Confirms if the contact has a phone number
  private final Boolean hasPhoneNumber;

  // Optional contact's photo
  private final Integer photoId;

  // Optional Logo
  private final Integer logoId;

  // Contact Number
  private final String contactNumber;

  // Email Id
  private final String emailId;

  public ContactData(Long contactId, String name, Boolean hasPhoneNumber, String contactNumber, String emailId,
      Integer photoId, Integer logoId) {
    super();
    this.contactId = contactId;
    this.name = name;
    this.hasPhoneNumber = hasPhoneNumber;
    this.contactNumber = contactNumber;
    this.emailId = emailId;
    this.photoId = photoId;
    this.logoId = logoId;
  }

  @Override
  public final ContactDataBuilder getBuilder() {
    return new ContactDataBuilder().setContactId(contactId).setName(name).setHasPhoneNumber(hasPhoneNumber)
        .setPhoneNumber(contactNumber).setPhotoId(photoId).setLogo(logoId);
  }

  @Override
  public final ContactDataBuilder newBuilder() {
    return new ContactDataBuilder();
  }

  @Override
  public final String toString() {
    return new StringBuilder().append("Contact Id : ").append(contactId).append(",\n").append("Name : ").append(name)
        .append(",\n").append("HAS_PHONE_NUMBER : ").append(hasPhoneNumber).append(",\n").append("Contact Number : ")
        .append(contactNumber).append(",\n").append("EmailId : ").append(emailId).append(",\n").append("PhotoID : ")
        .append(photoId).append(",\n").append("LogoID : ").append(logoId).append("\n").toString();
  }

  public final Long getContactId() {
    return contactId;
  }

  public final String getName() {
    return name;
  }

  public final Boolean hasPhoneNumber() {
    return hasPhoneNumber;
  }

  public final String getPhoneNumber() {
    return contactNumber;
  }

  public final Integer getPhotoId() {
    return photoId;
  }

  public final Integer getLogoId() {
    return logoId;
  }

  public final String getEmailId() {
    return emailId;
  }
}
