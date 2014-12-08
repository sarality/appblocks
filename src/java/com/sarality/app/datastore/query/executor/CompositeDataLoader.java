package com.sarality.app.datastore.query.executor;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.data.DataObject;
import com.sarality.app.datastore.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Loads a Composite Data and the associated data objects for it.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class CompositeDataLoader<T extends DataObject<T>, I, A>
    implements QueryExecutor<T> {

  private final QueryExecutor<T> queryExecutor;

  private final List<AssociatedDataQuery<List<A>, List<T>>> associatedQueryList =
      new ArrayList<AssociatedDataQuery<List<A>, List<T>>>();

  private final List<AssociatedDataValueSetter<T, DataObject.Builder<T>, I, A>> associatedDataValueSetterList =
      new ArrayList<AssociatedDataValueSetter<T, DataObject.Builder<T>, I, A>>();

  public CompositeDataLoader(QueryExecutor<T> queryExecutor) {
    this.queryExecutor = queryExecutor;
  }

  public CompositeDataLoader<T, I, A> withAssociatedQuery(
      AssociatedDataQuery<List<A>, List<T>> associatedQuery,
      AssociatedDataValueSetter<T, DataObject.Builder<T>, I, A> associatedDataValueSetter) {
    associatedQueryList.add(associatedQuery);
    associatedDataValueSetterList.add(associatedDataValueSetter);
    return this;
  }

  @Override
  public T execute(Query query) {
    T dataValue = queryExecutor.execute(query);

    DataObject.Builder<T> dataValueBuilder = dataValue.getBuilder();

    for (int i=0; i < associatedQueryList.size(); i++) {
      AssociatedDataQuery<List<A>, List<T>> associatedDataQuery = associatedQueryList.get(i);
      AssociatedDataValueSetter<T, DataObject.Builder<T>, I, A> associatedDataValueSetter
          = associatedDataValueSetterList.get(i);

      List<A> associatedDataList = associatedDataQuery.execute(Lists.of(dataValue));
      associatedDataValueSetter.indexAssociatedData(associatedDataList);
      I lookupValue = associatedDataValueSetter.getLookupValue(dataValue);
      associatedDataValueSetter.setAssociatedData(lookupValue, dataValueBuilder);
    }

    return dataValueBuilder.build();
  }
}
