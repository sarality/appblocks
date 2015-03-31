package com.sarality.app.datastore.contact;

public class ContactPhotoRequest {

  private final long contactId;
  private final PhotoType photoType;

  public enum PhotoType {
    THUMBNAIL,
    FULL_SIZE;
  }

  public ContactPhotoRequest(long contactId, PhotoType photoType) {
    this.contactId = contactId;
    this.photoType = photoType;
  }

  public long getContactId() {
    return contactId;
  }

  public PhotoType getPhotoType() {
    return photoType;
  }
}
