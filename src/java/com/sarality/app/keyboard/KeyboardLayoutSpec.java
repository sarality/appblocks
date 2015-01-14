package com.sarality.app.keyboard;

/**
 * Specification for a KeyboardLayout to be used within an Assistance Keyboard.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class KeyboardLayoutSpec {
  private final int activationKeyCode;
  private final int xmlLayoutResourceId;

  public KeyboardLayoutSpec(int activationKeyCode, int xmlLayoutResourceId) {
    this.activationKeyCode = activationKeyCode;
    this.xmlLayoutResourceId = xmlLayoutResourceId;
  }

  public int getActivationKeyCode() {
    return activationKeyCode;
  }

  public int getXmlLayoutResourceId() {
    return xmlLayoutResourceId;
  }
}
