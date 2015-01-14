package com.sarality.app.keyboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.sarality.app.view.web.AssistedWebView;

/**
 * A keyboard that provides
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class AssistanceKeyboardInitializer implements KeyboardStateChangeListener {

  private final Activity activity;
  private final KeyboardLayoutChanger keyboardLayoutChanger;
  private final KeyboardOutputComposer outputComposer;
  private final SoftKeyboardDetector softKeyboardDetector;

  @SuppressLint("SetJavaScriptEnabled")
  public AssistanceKeyboardInitializer(Activity activity, View view, KeyboardView keyboardView, AssistedWebView webView,
      KeyboardLayoutSpec... keyboardLayoutSpecs) {
    this.activity = activity;
    this.softKeyboardDetector = new SoftKeyboardDetector(view);
    this.outputComposer = new KeyboardOutputComposer(webView, keyboardView);
    this.keyboardLayoutChanger = new KeyboardLayoutChanger(keyboardView);

    // Configure the detector to listen to soft keyboard visibility changes
    this.softKeyboardDetector.setKeyboardStateChangeListener(this);

    // Set the Client on the WebView
    WebSettings settings = webView.getSettings();
    settings.setJavaScriptEnabled(true);
    webView.setWebViewClient(new WebViewClient());

    // Register the layout on the Layout Changer
    Context context = activity.getApplicationContext();
    for (KeyboardLayoutSpec spec : keyboardLayoutSpecs) {
      Keyboard keyboard = new Keyboard(context, spec.getXmlLayoutResourceId());
      this.keyboardLayoutChanger.registerKeyboard(spec.getActivationKeyCode(), keyboard);
    }
    keyboardView.setOnKeyboardActionListener(new KeyboardActionListener(keyboardLayoutChanger, outputComposer));
  }


  public void init(int initialLayoutPrimaryCode) {
    softKeyboardDetector.detect();
    keyboardLayoutChanger.handleKey(initialLayoutPrimaryCode);
  }

  @Override
  public void onKeyboardVisible() {
    SoftKeyboardHider.hideSoftKeyboard(activity);
    outputComposer.resetText();
  }
}
