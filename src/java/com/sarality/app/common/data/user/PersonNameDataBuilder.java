package com.sarality.app.common.data.user;

import com.sarality.app.data.DataObject;

/**
 * Builder for a PersonName.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class PersonNameDataBuilder implements DataObject.Builder<PersonNameData> {
  private String displayName;
  private String prefix;
  private String givenName;
  private String middleName;
  private String familyName;
  private String suffix;

  @Override
  public PersonNameData build() {
    return new PersonNameData(displayName, prefix, givenName, middleName, familyName, suffix);
  }

  /**
   * Set full display name for the Person.
   * 
   * @param displayName String display name.
   * @return Builder with the full display name set.
   */
  public PersonNameDataBuilder setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  /**
   * Set the Prefix if any for the person's name.
   * 
   * @param prefix String prefix for the Name
   * @return Builder with the prefix set.
   */
  public PersonNameDataBuilder setPrefix(String prefix) {
    this.prefix = prefix;
    return this;
  }

  /**
   * Set the first / given name of the Person name that is being built.
   * 
   * @param givenName First name of the person.
   * @return Builder with the first name set.
   */
  public PersonNameDataBuilder setGivenName(String givenName) {
    this.givenName = givenName;
    return this;
  }

  /**
   * Set the middle name of the Person name that is being built.
   * 
   * @param middleName Middle name of the person.
   * @return Builder with the middle name set.
   */
  public PersonNameDataBuilder setMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  /**
   * Set the last / family name of the Person name that is being built.
   * 
   * @param familyName Last name of the person.
   * @return Builder with the last name set.
   */
  public PersonNameDataBuilder setFamilyName(String familyName) {
    this.familyName = familyName;
    return this;
  }

  /**
   * Set the Suffix is any for the Person.
   * 
   * @param suffix String suffix for the person's name.
   * @return Builder with the suffix set.
   */
  public PersonNameDataBuilder setSuffix(String suffix) {
    this.suffix = suffix;
    return this;
  }
}
