package com.sarality.app.datastore.contact;

import com.sarality.app.common.data.user.PersonNameData;
import com.sarality.app.common.data.user.PersonNameDataBuilder;
import com.sarality.app.data.DataObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Builds the Contact Data
 *
 * @author sunayna(Sunayna Uberoy)
 */
public class ContactDataBuilder implements DataObject.Builder<ContactData> {

  // Id of the contact
  private Long contactId;

  // Name of the person
  private PersonNameDataBuilder nameBuilder = new PersonNameDataBuilder();

  // Does the contact have a phone number
  private Boolean hasPhoneNumber;

  // Photo ID of the contact
  private Integer photoId;

  private Integer logoId;

  private List<ContactNumber> contactNumberList = new ArrayList<ContactNumber>();

  private List<String> emailIdList = new ArrayList<String>();

  private List<WhatsAppNumber> whatsAppNumberList = new ArrayList<WhatsAppNumber>();

  @Override
  public ContactData build() {
    PersonNameData personName = null;
    if (nameBuilder != null) {
      personName = nameBuilder.build();
    }
    return new ContactData(contactId, personName, hasPhoneNumber, contactNumberList, emailIdList,
        whatsAppNumberList, photoId, logoId);
  }

  public final ContactDataBuilder setContactId(Long contactId) {
    this.contactId = contactId;
    return this;
  }

  public final ContactDataBuilder setName(PersonNameData name) {
    this.nameBuilder = name.getBuilder();
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

  public final ContactDataBuilder addWhatsAppNumber(WhatsAppNumber whatsAppNumber) {
    this.whatsAppNumberList.add(whatsAppNumber);
    return this;
  }

  public final ContactDataBuilder setWhatsAppNumberList(List<WhatsAppNumber> whatsAppNumberList) {
    this.whatsAppNumberList.addAll(whatsAppNumberList);
    return this;
  }

  public PersonNameDataBuilder getName() {
    return this.nameBuilder;
  }

  public final ContactDataBuilder setName(PersonNameDataBuilder nameBuilder) {
    this.nameBuilder = nameBuilder;
    return this;
  }

}
