package com.sarality.app.loader;

/**
 * Async Response interface to notify the calling entity when the asyncTask is completed
 *
 * @author Sunayna Uberoy
 */
public interface OnTaskCompleteListener<T> {
  void processFinish(T output);
}
