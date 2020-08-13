package com.recketmq.batch.producer;

import com.recketmq.order.entity.Order;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther ChenShuHong
 * @Date 2020-08-10 15:32
 * 批量发送
 */
public class Producer {
  public static void main(String[] args)throws Exception {
    DefaultMQProducer producer = new DefaultMQProducer("group1");
    producer.setNamesrvAddr("120.78.86.102:9876;120.78.86.102:9877");
    producer.start();
    List<Order> list = Order.getList();
    String topic = "BatchTest";
    List<Message> messages = new ArrayList();
    messages.add(new Message(topic, "Tag", "OrderID001", "Hello world 0".getBytes()));
    messages.add(new Message(topic, "Tag", "OrderID002", "Hello world 1".getBytes()));
    messages.add(new Message(topic, "Tag", "OrderID003", "Hello world 2".getBytes()));
    producer.send(messages);
    producer.shutdown();
    System.out.println("发送成功！！");
  }
}
