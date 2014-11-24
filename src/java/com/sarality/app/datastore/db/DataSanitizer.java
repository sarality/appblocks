package com.sarality.app.datastore.db;

import com.sarality.app.data.DataObject;
import com.sarality.app.error.ValidationException;

/**
 * Sanitizes the data before it an be stored in a Table.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface DataSanitizer<T extends DataObject<T>> {

  /**
   * Sanitize the given data object.
   *
   * @param data Data Object to be Sanitized.
   * @return A sanitized data object.
   */
  T sanitize(T data);
}
