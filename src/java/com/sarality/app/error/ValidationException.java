package com.sarality.app.error;

public class ValidationException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 3392788082871974131L;

  public ValidationException() {
    super();
  }

  public ValidationException(String detailMessage, Throwable throwable) {
    super(detailMessage, throwable);
  }

  public ValidationException(String detailMessage) {
    super(detailMessage);
  }

  public ValidationException(Throwable throwable) {
    super(throwable);
  }
}
