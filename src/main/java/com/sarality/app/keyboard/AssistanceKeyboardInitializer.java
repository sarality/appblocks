package com.sarality.app.keyboard;

import android.annotation.SuppressLint;
import android.app.Activity;
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
public abstract class AssistanceKeyboardInitializer implements SoftKeyboardVisibilityListener {

  private final Activity activity;
  private final KeyboardLayoutChanger keyboardLayoutChanger;
  private final KeyboardOutputComposer outputComposer;
  private final SoftKeyboardDetector softKeyboardDetector;
  private final KeyboardKeysInitializer keyboardKeysInitializer;

  @SuppressLint("SetJavaScriptEnabled")
  public AssistanceKeyboardInitializer(Activity activity, View view, KeyboardView keyboardView,
      AssistedWebView webView) {
    this.activity = activity;
    this.softKeyboardDetector = new SoftKeyboardDetector(view);
    this.outputComposer = new KeyboardOutputComposer(webView, keyboardView);
    this.keyboardLayoutChanger = new KeyboardLayoutChanger(keyboardView);
    this.keyboardKeysInitializer = new KeyboardKeysInitializer();

    // Configure the detector to listen to soft keyboard visibility changes
    this.softKeyboardDetector.setKeyboardVisibilityListener(this);

    // Set the Client on the WebView
    WebSettings settings = webView.getSettings();
    settings.setJavaScriptEnabled(true);
    webView.setWebViewClient(new WebViewClient());

    keyboardView.setOnKeyboardActionListener(new KeyboardActionListener(keyboardLayoutChanger, outputComposer));
  }

  protected void registerLayout(KeyboardLayoutSpec spec) {
    Keyboard keyboard = new Keyboard(activity.getApplicationContext(), spec.getXmlLayoutResourceId());
    this.keyboardLayoutChanger.registerKeyboard(spec.getActivationKeyCode(), keyboard);
    this.keyboardKeysInitializer.register(spec.getName(), keyboard, spec.getKeyValues());
  }

  public void init(int initialLayoutPrimaryCode) {
    softKeyboardDetector.detect();
    keyboardKeysInitializer.init();
    keyboardLayoutChanger.handleKey(initialLayoutPrimaryCode);
  }

  @Override
  public void onKeyboardVisible() {
    SoftKeyboardHider.hideSoftKeyboard(activity);
    outputComposer.resetText();
  }
}
