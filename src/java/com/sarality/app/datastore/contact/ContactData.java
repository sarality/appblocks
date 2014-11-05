package com.sarality.app.datastore.contact;

import java.util.ArrayList;
import java.util.List;

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
  private final List<ContactNumber> contactNumbers;

  // Email Id
  private final List<String> emailIds;

  public ContactData(Long contactId, String name, Boolean hasPhoneNumber, List<ContactNumber> contactNumbers,
      List<String> emailIds, Integer photoId, Integer logoId) {
    super();
    this.contactId = contactId;
    this.name = name;
    this.hasPhoneNumber = hasPhoneNumber;
    this.contactNumbers = new ArrayList<ContactNumber>();
    this.contactNumbers.addAll(contactNumbers);
    this.emailIds = new ArrayList<String>();
    this.emailIds.addAll(emailIds);
    this.photoId = photoId;
    this.logoId = logoId;
  }

  @Override
  public final ContactDataBuilder getBuilder() {
    return new ContactDataBuilder().setContactId(contactId).setName(name).setHasPhoneNumber(hasPhoneNumber)
        .addPhoneNumber(contactNumbers.toArray(new ContactNumber[contactNumbers.size()])).setPhotoId(photoId)
        .setLogo(logoId);
  }

  @Override
  public final ContactDataBuilder newBuilder() {
    return new ContactDataBuilder();
  }

  @Override
  public final String toString() {
    return new StringBuilder().append("Contact Id : ").append(contactId).append(",\n").append("Name : ").append(name)
        .append(",\n").append("HAS_PHONE_NUMBER : ").append(hasPhoneNumber).append(",\n").append("Contact Number : ")
        .append(contactNumbers.toArray()).append(",\n").append("EmailId : ").append(emailIds.toArray()).append(",\n")
        .append("PhotoID : ").append(photoId).append(",\n").append("LogoID : ").append(logoId).append("\n").toString();
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

  public final List<ContactNumber> getPhoneNumber() {
    return contactNumbers;
  }

  public final Integer getPhotoId() {
    return photoId;
  }

  public final Integer getLogoId() {
    return logoId;
  }

  public final List<String> getEmailIds() {
    return emailIds;
  }
}
