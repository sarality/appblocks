package com.sarality.app.loader;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to load data from a Table along with associated data.
 *
 * <p/>
 *
 * Usage:
 * 1) Initialize the Combiner with the data for the primary objects.
 * 2) Index this object using a Field (by passing in the FieldValueGetter for the field)
 * 3) Combine with list of Associated Data.
 * 4) Repeat 3 for multiple associated data object
 * 5) Access combined data
 *
 * <code>
 *   List<T> data = tableDataLoader.loadAll();
 *   List<A> associatedData = associatedTableDataLoader.loadAll();
 *
 *   AssociatedDataCombiner combiner = new AssociatedDataCombiner(data, dataBuilderConverter);
 *   combiner.index(DataObject.ID_FIELD);
 *
 *   combiner.combine(associatedData, AssociatedDataObject.JOIN_FIELD, DataObject.ASSOCIATED_DATA_FIELD);
 *
 *   List<T> combinedData = combiner.buildAll();
 *
 * </code>
 * @author abhideep@ (Abhidep Singh)
 */
public class AssociatedDataCombiner<T, B, K> {

  private final List<T> dataList  = new ArrayList<T>();
  private final List<B> dataBuilderList = new ArrayList<B>();
  private final BuilderConverter<T, B> converter;

  private DataBuilderIndexer<T, B, K> primaryDataIndexer;

  /**
   * Constructor to combine associated data for a single data object.
   *
   * @param data Data object to add associated data to.
   * @param converter Converts a data object to a builder and vice versa.
   */
  public AssociatedDataCombiner(T data, BuilderConverter<T, B> converter) {
    if (data != null) {
      this.dataList.add(data);
      this.dataBuilderList.add(converter.getBuilder(data));
    }
    this.converter = converter;
  }

  /**
   * Constructor to combine associated data for a list of data objects.
   *
   * @param dataList List of Data objects to add associated data to.
   * @param converter Converts a data object to a builder and vice versa.
   */
  public AssociatedDataCombiner(List<T> dataList, BuilderConverter<T, B> converter) {
    for (T data : dataList) {
      this.dataList.add(data);
      this.dataBuilderList.add(converter.getBuilder(data));
    }
    this.converter = converter;
  }

  /**
   * Index the primary data objects (to which we are adding associated data) using the given field.
   *
   * @param indexFieldValueGetter Getter for the Field that is used to index the data.
   */
  public void index(FieldValueGetter<T, K> indexFieldValueGetter) {
    this.primaryDataIndexer = new DataBuilderIndexer<T, B, K>(indexFieldValueGetter);
    for (int i = 0 ; i < dataList.size(); i++) {
      primaryDataIndexer.index(dataList.get(i), dataBuilderList.get(i));
    }
  }

  /**
   * Add associated data to builders of the master data (or list of master data)
   *
   * @param associatedDataList List of Associated Data objects
   * @param associatedDataIndexFieldValueGetter Getter for the join field - the fiekd in the associated data object
   *                                            that matches the value of the field in the master object.
   * @param associatedDataSetter Getter of the field in the master object that stores the associated object.
   * @param <A> Type of associated object
   */
  public <A> void combine(List<A> associatedDataList, FieldValueGetter<A, K> associatedDataIndexFieldValueGetter,
      FieldValueSetter<B, A> associatedDataSetter) {
    for (A associatedData : associatedDataList) {
      K key = associatedDataIndexFieldValueGetter.getValue(associatedData);
      B builder = primaryDataIndexer.getBuilderForKey(key);
      associatedDataSetter.setValue(builder, associatedData);
    }
  }

  /**
   * Build the data object after it has been combined with the associated data objects.
   *
   * @return The combined data object.
   */
  public T build() {
    if (dataBuilderList.isEmpty() || dataBuilderList.size() > 1) {
      throw new IllegalAccessError("Cannot build data object when combiner is initialized with a list of objects. " +
          "Call buildAll instead.");
    }
    return buildAll().get(0);
  }

  /**
   * Build all data objects after they have been combined with the associated data objects.
   *
   * @return List of data objects after combining.
   */
  public List<T> buildAll() {
    List<T> list = new ArrayList<T>();
    for (B builder : dataBuilderList) {
      list.add(converter.build(builder));
    }
    return list;
  }

  /**
   * Get all builder for data object after they have been combined with the associated data object.
   *
   * @return List of Builder after combining.
   */
  public List<B> getAllBuilders() {
    return dataBuilderList;
  }

}
