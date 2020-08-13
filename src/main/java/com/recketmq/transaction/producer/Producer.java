package com.recketmq.transaction.producer;

import com.recketmq.order.entity.Order;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther ChenShuHong
 * @Date 2020-08-10 15:32
 * 事务mq
 */
public class Producer {
  public static void main(String[] args)throws Exception {

    TransactionMQProducer  producer = new TransactionMQProducer("group1");
    producer.setNamesrvAddr("120.78.86.102:9876;120.78.86.102:9877");
    //事务监听器
    producer.setTransactionListener(new TransactionListener() {
      //在该方法中执行本地事务
      public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        if(new String(msg.getBody()).equals("Hello world 0")){
          System.out.println("正常发送");
          return  LocalTransactionState.COMMIT_MESSAGE;
        }else if(new String(msg.getBody()).equals("Hello world 1")){
          return  LocalTransactionState.ROLLBACK_MESSAGE;
        }else {
          System.out.println("正常发送ss");
          return  LocalTransactionState.UNKNOW;
        }

      }
      //mq进行消息事务状态回查
      public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        System.out.println("消息"+msg.getTags());
        return LocalTransactionState.COMMIT_MESSAGE;
      }
    });
    producer.start();
    List<Order> list = Order.getList();
    String topic = "transation";

    producer.sendMessageInTransaction(new Message(topic, "Tag11", "OrderID001", "Hello world 0".getBytes()),null);
   // producer.shutdown();
    System.out.println("发送成功！！");
  }
}
