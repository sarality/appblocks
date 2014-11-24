package com.sarality.app.datastore.db;

import com.sarality.app.data.DataObject;
import com.sarality.app.error.ValidationException;

/**
 * Interface to validate that the given data object can be stored in a Table.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface DataValidator<T extends DataObject<T>> {

  boolean validate(T data) throws ValidationException;
}
