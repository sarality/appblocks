package com.sarality.app.view.fragment.action;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Fragment that has the capability of being refreshed with a new set of arguments
 *
 * @author sunayna@ (Sunayna Uberoy)
 */
public abstract class RefreshableFragment extends Fragment {

  public abstract void refresh(Bundle bundle);
}

