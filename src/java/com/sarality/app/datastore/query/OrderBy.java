package com.sarality.app.datastore.query;

import com.sarality.app.datastore.Column;

/**
 * The order in which the query results would be returned
 * @author sunayna@dothat.in sunayna
 *
 */
public class OrderBy {

  /**
   * Ascending or Descending order 
   * @author sunayna@dothat.in sunayna
   *
   */
  public  enum Order{
    ASC(" ASC"), 
    DESC(" DESC");

    private final String order;

    private Order(String order) {
      this.order = order;
    }

    public String getSqlString() {
      return order;
    }
  }

  // the Column to be ordered by
  private final Column col;
  // The order to be used
  private Order order;
  
  /**
   * Constructor
   * @param col
   */
  OrderBy(Column col){
    this.col = col;
  }
  
  /**
   * Sets the sequence 
   * @param orderByEnum
   * @return
   * current instance 
   */
  public OrderBy setOrderBy(Order  orderByEnum){
    this.order = orderByEnum;
    return this;
  }
  
  /**
   * Converts to string
   */
  public String toString(){
    return col.toString() + order.getSqlString();
  }
}
