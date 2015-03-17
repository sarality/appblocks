package com.sarality.app.datastore.query.executor;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.data.DataObject;
import com.sarality.app.datastore.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Loads a list of Composite Data Objects and the associated data object for each.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class CompositeDataListLoader<T extends DataObject<T>, I, A>
    implements QueryExecutor<List<T>> {

  private final QueryExecutor<List<T>> queryExecutor;

  private final List<AssociatedDataQuery<List<A>, List<T>>> associatedDataQueryList =
      new ArrayList<AssociatedDataQuery<List<A>, List<T>>>();

  private final List<AssociatedDataValueSetter<T, DataObject.Builder<T>, I, A>> dataCombinerList =
      new ArrayList<AssociatedDataValueSetter<T, DataObject.Builder<T>, I, A>>();

  public CompositeDataListLoader(QueryExecutor<List<T>> queryExecutor) {
    this.queryExecutor = queryExecutor;
  }

  public CompositeDataListLoader<T, I, A> withJoinQuery(
      AssociatedDataQuery<List<A>, List<T>> associatedDataQuery,
      AssociatedDataValueSetter<T, DataObject.Builder<T>, I, A> dataCombiner) {
    associatedDataQueryList.add(associatedDataQuery);
    dataCombinerList.add(dataCombiner);
    return this;
  }

  @Override
  public List<T> execute(Query query) {
    List<T> dataValueList = queryExecutor.execute(query);

    List<DataObject.Builder<T>> valueBuilderList = new ArrayList<DataObject.Builder<T>>();
    for (T dataValue : dataValueList) {
      valueBuilderList.add(dataValue.getBuilder());
    }

    for (int i = 0; i < associatedDataQueryList.size(); i++) {
      AssociatedDataQuery<List<A>, List<T>> associatedDataQuery = associatedDataQueryList.get(i);
      AssociatedDataValueSetter<T, DataObject.Builder<T>, I, A> dataCombiner = dataCombinerList.get(i);

      List<A> associatedValueList = associatedDataQuery.execute(dataValueList);
      dataCombiner.indexAssociatedData(associatedValueList);
      for (int j = 0; j < dataValueList.size(); j++) {
        T dataValue = dataValueList.get(j);
        DataObject.Builder<T> valueBuilder = valueBuilderList.get(j);
        dataCombiner.setAssociatedData(dataCombiner.getLookupValue(dataValue), valueBuilder);
      }
    }

    List<T> returnValueList = Lists.of();
    for (DataObject.Builder<T> valueBuilder : valueBuilderList) {
      returnValueList.add(valueBuilder.build());
    }
    return returnValueList;
  }
}
