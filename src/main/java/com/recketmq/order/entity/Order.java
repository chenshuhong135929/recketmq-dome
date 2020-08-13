package com.recketmq.order.entity;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther ChenShuHong
 * @Date 2020-08-10 15:26
 */
public class Order {
  private String id;
  private String orderCode;

  public static  List<Order> getList(){
    List<Order> list = Arrays.asList(
         new Order("0","0号")
        ,new Order("1","1号")
        ,new Order("2","2号")
        ,new Order("3","3号")
        ,new Order("4","4号")
        ,new Order("5","5号")
        ,new Order("6","6号")
        ,new Order("7","7号")
        ,new Order("8","8号")
        ,new Order("9","9号")
    );
    return  list;
  }

  public Order() {
  }

  public Order(String id, String orderCode) {
    this.id = id;
    this.orderCode = orderCode;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getOrderCode() {
    return orderCode;
  }

  public void setOrderCode(String orderCode) {
    this.orderCode = orderCode;
  }

}
