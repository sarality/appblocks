package com.sarality.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

/**
 * Checks if the flag for the feature exists in the AndroidManifest.xml file and its value is set to true.
 * <p>
 * Returns true if feature is enabled, false otherwise.
 * <p>
 * Returns the default value if the flag does not exist in the manifest file.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class FeatureEnabled implements AppStateValue<Boolean> {

  private static final Logger logger = LoggerFactory.getLogger(FeatureEnabled.class);

  private final Context context;
  private final String flagName;
  private final boolean defaultValue;

  /**
   * Constructor.
   * 
   * @param context
   * @param flagName Name of Flag associated with the feature
   * @param defaultValue Default value to be used if flag does not exist or cannot be extracted.
   */
  public FeatureEnabled(Context context, String flagName, boolean defaultValue) {
    this.context = context;
    this.flagName = flagName;
    this.defaultValue = defaultValue;
  }

  @Override
  public Boolean getValue() {
    try {
      ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(
          context.getApplicationContext().getPackageName(), PackageManager.GET_META_DATA);
      Bundle bundle = appInfo.metaData;
      return bundle.getBoolean(flagName);
    } catch (NullPointerException e) {
      logger.info("Flag with name {} not found. Assuming default value of {}", flagName, defaultValue);
      return false;
    } catch (NameNotFoundException e) {
      logger.error("Error loading Application info. Assuming default value of {} for flag {}", defaultValue, flagName);
      return false;
    }
  }

  @Override
  public boolean isEditable() {
    return false;
  }

  @Override
  public void setValue(Boolean value) {
    // No op
  }
}
