package com.sarality.app.datastore.contact;

/**
 * Class that identifies an app and stores number and contactId that will be used to open the app
 *
 * @author sunayna (Sunayna Uberoy)
 */
public class AppContact {
  private final String number;
  private final AppEnum app;
  private final Long appContactId;

  AppContact(AppEnum app, String number, Long appContactId) {
    this.app = app;
    this.number = number;
    this.appContactId = appContactId;
  }

  public String getNumber() {
    return number;
  }

  public AppEnum getApp() {
    return app;
  }

  public Long getAppContactId() {
    return appContactId;
  }
}
