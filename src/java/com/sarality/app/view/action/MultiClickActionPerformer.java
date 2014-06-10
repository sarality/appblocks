package com.sarality.app.view.action;

import java.util.List;

import android.view.View;

import com.sarality.app.base.registry.MultiValueRegistry;
import com.sarality.app.base.registry.Registry.Entry;

public class MultiClickActionPerformer<T> extends MultiValueRegistry<View, ClickActionPerformer<T>> implements
    View.OnClickListener {

  List<View.OnClickListener> listeners;
  private final ClickActionListeners<T> viewOnClickListenerRegistry;

  public MultiClickActionPerformer() {
    super();
    viewOnClickListenerRegistry = new ClickActionListeners<T>();
  }

  @Override
  public void onClick(View v) {
    List<ClickActionPerformer<T>> listeners = viewOnClickListenerRegistry.getListener(v);
    for (ClickActionPerformer<T> listener : listeners) {
      listener.onClick(v);
    }
  }

  public void setupListener(View view, ViewAction<T> action) {
    viewOnClickListenerRegistry.setListener(view, action);
    super.register(viewOnClickListenerRegistry);
    view.setOnClickListener(this);
  }

}

class ClickActionListeners<T> extends MultiValueRegistry.EntryProvider<View, ClickActionPerformer<T>> {

  public ClickActionListeners() {
    super();
  }

  public void setListener(View view, ViewAction<T> action) {
    ClickActionPerformer<T> clickActionPerformer = new ClickActionPerformer<T>(action);
    addEntry(view, clickActionPerformer);
  }

  public List<ClickActionPerformer<T>> getListener(View view) {
    List<Entry<View, List<ClickActionPerformer<T>>>> entryList = provide();
    for (Entry<View, List<ClickActionPerformer<T>>> entry : entryList) {
      if (entry.getKey().equals(view)) {
        return entry.getValue();
      }
    }
    return null;
  }
}
