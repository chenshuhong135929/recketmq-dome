package com.recketmq.order.producer;

import com.alibaba.fastjson.JSON;
import com.recketmq.order.entity.Order;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByHash;
import org.apache.rocketmq.common.message.Message;

import java.util.List;

/**
 * @Auther ChenShuHong
 * @Date 2020-08-10 15:32
 */
public class Producer {
  public static void main(String[] args)throws Exception {
    DefaultMQProducer producer = new DefaultMQProducer("group1");
    producer.setNamesrvAddr("120.78.86.102:9876;120.78.86.102:9877");
    producer.start();
    List<Order> list = Order.getList();
    for (Order o:list){
      Object o1 = JSON.toJSON(o);
      Message msg = new Message("ordertopic","order",o.getId(),o1.toString().getBytes());
      //设置推迟发送的时间
     // msg.setDelayTimeLevel(5);
      SendResult send = producer.send(msg, new SelectMessageQueueByHash(), o.getId());
      System.out.println("发送结果"+ send);
      Thread.sleep(100);
    }
    producer.shutdown();
    System.out.println("发送成功！！");
  }
}
