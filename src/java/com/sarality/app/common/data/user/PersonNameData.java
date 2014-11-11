package com.sarality.app.common.data.user;

import com.sarality.app.data.DataObject;

/**
 * DataObject representing a Person's name.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class PersonNameData implements DataObject<PersonNameData> {

  private final String displayName;
  private final String givenName;
  private final String middleName;
  private final String familyName;
  private final String prefix;
  private final String suffix;

  /**
   * Constructor for a person's name.
   * 
   * @param givenName Given / First name of the person.
   * @param middleName Middle name of the person.
   * @param familyName Family / Last name of the person.
   */
  public PersonNameData(String displayName, String prefix, String givenName, String middleName, String familyName,
      String suffix) {
    this.displayName = displayName;
    this.givenName = givenName;
    this.middleName = middleName;
    this.familyName = familyName;
    this.prefix = prefix;
    this.suffix = suffix;
  }

  /**
   * Constructor for a person who only has a given name (First name).
   * 
   * @param givenName First Name for the person.
   */
  public PersonNameData(String givenName) {
    this(null, null, givenName, null, null, null);
  }

  /**
   * Constructor for a person who has no middle name.
   * 
   * @param givenName Given / First name for the person.
   * @param familyName Family / Last name for the person.
   */
  public PersonNameData(String givenName, String familyName) {
    this(null, null, givenName, null, familyName, null);
  }

  /**
   * @return Given / First name of the person as a String or null if no name is specified.
   */
  public final String getGivenName() {
    return givenName;
  }

  /**
   * @return Middle name for the person or null if the person doesn't have a middle name.
   */
  public final String getMiddleName() {
    return middleName;
  }

  /**
   * @return Family / Last name for the person or null if the person doesn't have a last name.
   */
  public final String getFamilyName() {
    return familyName;
  }

  /**
   * @return Full name of the person.
   */
  public final String getDisplayName() {
    return displayName;
  }

  /**
   * @return String prefix if any like Mr, Mrs, Dr
   */
  public String getPrefix() {
    return prefix;
  }

  /**
   * @return String suffix is any like Jr, Sr, III.
   */
  public String getSuffix() {
    return suffix;
  }

  @Override
  public PersonNameDataBuilder getBuilder() {
    return new PersonNameDataBuilder().setDisplayName(displayName).setPrefix(prefix).setGivenName(givenName)
        .setMiddleName(middleName).setFamilyName(familyName).setSuffix(suffix);
  }

  @Override
  public PersonNameDataBuilder newBuilder() {
    return new PersonNameDataBuilder();
  }
}
