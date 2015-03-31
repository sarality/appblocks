package com.sarality.app.view;

import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

/**
 * Interface for a View that has editable elements
 * <p/>
 * Used by classes that want to populate these elements or views.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface EditorView {

  public InputConnection getInputConnection();

  public EditorInfo getEditorInfo();
}
