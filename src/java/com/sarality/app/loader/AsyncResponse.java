package com.sarality.app.loader;

/**
 * Async Response interface to notify the calling entity when the asyncTask is completed
 *
 * @author Sunayna Uberoy
 */
public interface AsyncResponse<T> {
  void processFinish(int requestCode, T output);
}
