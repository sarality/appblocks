package com.sarality.app.datastore.contact;

import java.util.ArrayList;
import java.util.List;

import com.sarality.app.data.DataObject;

/**
 * Builds the Contact Data
 * 
 * @author sunayna(Sunayna Uberoy)
 * 
 */
public class ContactDataBuilder implements DataObject.Builder<ContactData> {

  // Id of the contact
  private Long contactId;

  // Name of the person
  private String name;

  // Does the contact have a phone number
  private Boolean hasPhoneNumber;

  // Photo ID of the contact
  private Integer photoId;

  private Integer logoId;

  private List<ContactNumber> contactNumberList = new ArrayList<ContactNumber>();

  private List<String> emailIdList = new ArrayList<String>();

  @Override
  public ContactData build() {
    return new ContactData(contactId, name, hasPhoneNumber, contactNumberList, emailIdList, photoId, logoId);
  }

  public final ContactDataBuilder setContactId(Long contactId) {
    this.contactId = contactId;
    return this;
  }

  public final ContactDataBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public final ContactDataBuilder setPhotoId(Integer photoId) {
    this.photoId = photoId;
    return this;
  }

  public final ContactDataBuilder setLogo(Integer logoId) {
    this.logoId = logoId;
    return this;
  }

  public final ContactDataBuilder setHasPhoneNumber(Boolean hasPhoneNumber) {
    this.hasPhoneNumber = hasPhoneNumber;
    return this;
  }

  public final ContactDataBuilder addPhoneNumber(ContactNumber contactNumber) {
    this.contactNumberList.add(contactNumber);
    return this;
  }

  public final ContactDataBuilder setPhoneNumberList(List<ContactNumber> contactNumberList) {
    this.contactNumberList.addAll(contactNumberList);
    return this;
  }

  public final ContactDataBuilder addEmailId(String emailId) {
    this.emailIdList.add(emailId);
    return this;
  }

  public final ContactDataBuilder setEmailList(List<String> emailList) {
    this.emailIdList.addAll(emailList);
    return this;
  }

}
