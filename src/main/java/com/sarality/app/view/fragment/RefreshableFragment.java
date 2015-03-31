package com.sarality.app.view.fragment;

import android.os.Bundle;

/**
 * Interface for Fragments that have the capability of being refreshed with a new set of arguments.
 *
 * @author sunayna@ (Sunayna Uberoy)
 */
public interface RefreshableFragment {

  public void refresh(Bundle bundle);
}

