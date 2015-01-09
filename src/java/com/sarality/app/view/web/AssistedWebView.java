package com.sarality.app.view.web;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.webkit.WebView;

import com.sarality.app.view.EditorView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A WevView that allows an AssistanceKeyboard to be attached to it.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class AssistedWebView extends WebView implements EditorView {

  private static final Logger logger = LoggerFactory.getLogger(AssistedWebView.class);

  private InputConnection inputConnection = null;
  private EditorInfo editorInfo = null;

  public AssistedWebView(Context context) {
    super(context);
  }

  public AssistedWebView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public AssistedWebView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  public AssistedWebView(Context context, AttributeSet attrs, int defStyle, boolean privateBrowsing) {
    super(context, attrs, defStyle, privateBrowsing);
  }

  @Override
  public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
    logger.debug("Saving reference to Web View's Input Connection and Editor Info");
    this.inputConnection = super.onCreateInputConnection(editorInfo);
    this.editorInfo = editorInfo;
    return inputConnection;
  }

  @Override
  public InputConnection getInputConnection() {
    return inputConnection;
  }

  @Override
  public EditorInfo getEditorInfo() {
    return editorInfo;
  }

  
}
