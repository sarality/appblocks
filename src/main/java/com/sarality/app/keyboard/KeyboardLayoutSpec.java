package com.sarality.app.keyboard;

/**
 * Specification for a KeyboardLayout to be used within an Assistance Keyboard.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class KeyboardLayoutSpec {
  private final String name;
  private final int xmlLayoutResourceId;
  private final int activationKeyCode;
  private final KeyValues<?> keyValues;

  public KeyboardLayoutSpec(String name, int xmlLayoutResourceId, int activationKeyCode, KeyValues<?> keyValues) {
    this.name = name;
    this.xmlLayoutResourceId = xmlLayoutResourceId;
    this.activationKeyCode = activationKeyCode;
    this.keyValues = keyValues;
  }

  public int getActivationKeyCode() {
    return activationKeyCode;
  }

  public int getXmlLayoutResourceId() {
    return xmlLayoutResourceId;
  }

  public KeyValues<?> getKeyValues() {
    return keyValues;
  }

  public String getName() {
    return name;
  }
}
