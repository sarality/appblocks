package com.sarality.app.datastore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Query to fetch data from a DataStore.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class Query {
  private final List<Column> whereColumnList = new ArrayList<Column>();
  private final List<String> whereColumnValueList = new ArrayList<String>();

  private final List<Column> columns;

  public Query(List<Column> columns) {
    this.columns = columns;
  }

  public Query(Column... columns) {
    this(Arrays.asList(columns));
  }

  /**
   * Constructor for a Query that fetches all Columns from the DataStore.
   * 
   * @param table
   */
  public Query(DataStore<?> store) {
    this(store.getColumns());
  }

  public Query withFilter(Column column, String value) {
    whereColumnList.add(column);
    whereColumnValueList.add(value);
    return this;
  }

  public List<Column> getColumns() {
    return columns;
  }
  
  public String getWhereList(){
    StringBuilder whereClause = new StringBuilder();
    Iterator<Column> iterate = whereColumnList.iterator();
    while(iterate.hasNext()){
      whereClause.append(iterate.next().getName() + "= ?");
      if(iterate.hasNext()){
        whereClause.append(",");
      }
    }
    return whereClause.toString();
  }
  
  public String[] getWhereValueList(){
    return whereColumnValueList.toArray(new String[whereColumnValueList.size()]);
  }
}
