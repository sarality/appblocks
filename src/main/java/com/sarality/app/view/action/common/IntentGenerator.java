package com.sarality.app.view.action.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Interface for classes that generate an Intent that is used to start an activity or fragment.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public interface IntentGenerator {

  /**
   * Generate a new Intent based on data in the given View.
   *
   * @param view The View that the Intent extracts data from.
   * @return The Intent that can be passed to a Fragment or Activity.
   */
  public Intent generate(View view);
}
