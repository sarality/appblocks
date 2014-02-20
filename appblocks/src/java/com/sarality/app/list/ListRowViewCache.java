package com.sarality.app.list;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;
import android.view.View;

public class ListRowViewCache {

  private static final String TAG = "com.sarality.app.list.ListRowViewCache";

  private final Map<Integer, View> viewCache = new HashMap<Integer, View>();

  public View getViewById(View rowView, int viewId) {
    View view = viewCache.get(viewId);
    if (view == null) {
      Log.w(TAG, "Row Item View with Id " + viewId + " not found in Cache. "
          + "Consideing caching elememts that you access in the renderer since it can improve "
          + "efficiency by 15%");
      return rowView.findViewById(viewId);
    } else {
      Log.d(TAG, "Found Row Item View with Id " + viewId + " in Cache.");
      return view;
    }
  }

  public void cacheViewWithId(View rowView, int viewId) {
    Log.d(TAG, "Caching Row Item View with Id " + viewId);
    viewCache.put(viewId, rowView);
  }
  
  @Override
  public String toString() {
    return viewCache.toString();
  }

  @Override
  public int hashCode() {
    return viewCache.hashCode();
  }
}
