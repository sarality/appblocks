package com.sarality.app.datastore;

import java.util.ArrayList;
import java.util.List;

public class ColumnUtils {

  public static List<Column> getAllColumns(Column[] columns) {
    List<Column> columnList = new ArrayList<Column>();
    for (Column column : columns) {
      columnList.add(column);
    }
    return columnList;
  }
  
}
