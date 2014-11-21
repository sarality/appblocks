package com.sarality.app.view.nav;

import android.os.Bundle;
import android.view.View;

/**
 * Interface that would generator bundle from a view
 * 
 * @author sunayna(Sunayna Uberoy)
 */
public interface BundleGenerator {

  // Generate Bundle
  public Bundle generate(View view);
}
