package com.sarality.app.list;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public abstract class BaseListRowRenderer<T> implements ListRowRenderer<T> {

  private final boolean supressRowLevelTouchEvents;

  public BaseListRowRenderer(boolean supressRowLevelTouchEvents) {    
    this.supressRowLevelTouchEvents = supressRowLevelTouchEvents;
  }

  public BaseListRowRenderer() {
    this(true);
  }

  private final OnTouchListener suppressRowLevelTouchEventListener = new OnTouchListener() {
    @Override
    public boolean onTouch(View view, MotionEvent event) {
      Log.i("BaseListRowRenderer", "Processing Touch event for view with Id " + view.getId() 
          + " and view " + view);
      return true;
    }
  };

  @Override
  public void setupActions(View rowView, ListRowViewCache rowViewCache, T value) {
    if (supressRowLevelTouchEvents) {
      rowView.setOnTouchListener(suppressRowLevelTouchEventListener);      
    }
  }
}
