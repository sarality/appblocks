package com.sarality.app.datastore.contact;

import com.sarality.app.common.data.user.PersonNameData;
import com.sarality.app.data.DataObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple Data for a contact
 *
 * @author sunayna(Sunayna Uberoy)
 */
public class ContactData implements DataObject<ContactData> {

  // Id of the Contact
  private final Long contactId;

  // Name of the contact
  private final PersonNameData name;

  // Confirms if the contact has a phone number
  private final Boolean hasPhoneNumber;

  // Optional contact's photo
  private final Integer photoId;

  // Optional Logo
  private final Integer logoId;

  // Contact Number
  private final List<ContactNumber> contactNumberList;

  // Email Id
  private final List<String> emailIdList;

  //WhatsApp Number
  private final List<AppContact> appContactList;

  // TODO Add Label to ContactNumber
  // TODO emailAddress to also have its own class
  public ContactData(Long contactId, PersonNameData name, Boolean hasPhoneNumber,
                     List<ContactNumber> contactNumberList, List<String> emailIdList,
                     List<AppContact> appContactList, Integer photoId,
                     Integer logoId) {
    super();
    this.contactId = contactId;
    this.name = name;
    this.hasPhoneNumber = hasPhoneNumber;
    this.contactNumberList = new ArrayList<ContactNumber>();
    this.contactNumberList.addAll(contactNumberList);
    this.emailIdList = new ArrayList<String>();
    this.emailIdList.addAll(emailIdList);
    this.appContactList = new ArrayList<AppContact>();
    this.appContactList.addAll(appContactList);
    this.photoId = photoId;
    this.logoId = logoId;
  }

  @Override
  public final ContactDataBuilder getBuilder() {
    return new ContactDataBuilder().setContactId(contactId).setName(name).setHasPhoneNumber(hasPhoneNumber)
        .setPhoneNumberList(contactNumberList).setPhotoId(photoId).setLogo(logoId).setAppContactList(appContactList);
  }

  @Override
  public final ContactDataBuilder newBuilder() {
    return new ContactDataBuilder();
  }

  @Override
  public final String toString() {
    return new StringBuilder().append("Contact Id : ").append(contactId).append(",\n").append("Name : ").append(name)
        .append(",\n").append("HAS_PHONE_NUMBER : ").append(hasPhoneNumber).append(",\n").append("Contact Number : ")
        .append(contactNumberList.toArray()).append(",\n").append("EmailId : ").append(emailIdList.toArray())
        .append(",\n").append("PhotoID : ").append(photoId).append(",\n").append("LogoID : ").append(logoId)
        .append("\n").toString();
  }

  public final Long getContactId() {
    return contactId;
  }

  public final PersonNameData getName() {
    return name;
  }

  public final Boolean hasPhoneNumber() {
    return hasPhoneNumber;
  }

  public final List<ContactNumber> getPhoneNumberList() {
    return contactNumberList;
  }

  public final Integer getLogoId() {
    return logoId;
  }

  public final List<String> getEmailIdList() {
    return emailIdList;
  }

  public final List<AppContact> getAppContactList() {
    return appContactList;
  }
}
