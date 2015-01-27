package com.sarality.app.view.list;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;

/**
 * Cache of elements inside a ListView row.
 * <p>
 * Makes it more efficient to set data on the view rather than do a lookupById
 * each time.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class ListRowViewCache {

  private static final String TAG = "ListRowViewCache";

  // Cache of ViewId and View
  private final SparseArray<View> viewCache = new SparseArray<View>();

  /**
   * Returns the View for the given ViewId.
   * <p>
   * Returns the cached version or performs a lookup if no view is cached.
   * 
   * @param rowView The View for the row to lookup elements in.
   * @param viewId Id of the view to lookup.
   * @return The element with the given Id.
   */
  public View getViewById(View rowView, int viewId) {
    View view = getCacheViewId(viewId);
    if (view == null) {
      Log.w(TAG, "Row Item View with Id " + viewId + " not found in Cache. "
          + "Consideing caching elememts that you access in the renderer since it can improve "
          + "efficiency by 15%");
      return rowView.findViewById(viewId);
    } else {
      if (Log.isLoggable(TAG, Log.DEBUG)) {
        Log.d(TAG, "Found Row Item View with Id " + viewId + " in Cache.");
      }
      return view;
    }
  }

  /**
   * Lookup view in the given row View and cache it.
   * 
   * @param rowView The View to lookup the view id.
   * @param viewId The id of the view to lookup.
   */
  public void cacheViewWithId(View rowView, int viewId) {
    View view = rowView.findViewById(viewId);
    if (view != null) {
      if (Log.isLoggable(TAG, Log.DEBUG)) {
        Log.d(TAG, "Caching Row Item View with Id " + viewId + " in row with Id " + rowView.getId());
      }
      viewCache.put(viewId, view);
    }
  }

  public View getCacheViewId(int viewId){
    return viewCache.get(viewId);
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
