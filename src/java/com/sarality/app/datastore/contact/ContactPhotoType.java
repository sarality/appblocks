package com.sarality.app.datastore.contact;

/**
 * Holds contactId and type of Photo
 * 
 * @author sunayna (Sunayna Uberoy)
 */
public class ContactPhotoType {

  private final long contactId;
  private final PhotoType photoType;

  /**
   * Size of the photo
   * 
   * @author sunayna(Sunayna Uberoy)
   */
  public enum PhotoType {
    THUMBNAIL,
    FULL_SIZE;
  }

  public ContactPhotoType(long contactId, PhotoType photoType) {
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
