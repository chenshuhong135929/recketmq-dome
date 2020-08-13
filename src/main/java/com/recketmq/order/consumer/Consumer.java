package com.recketmq.order.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @Auther ChenShuHong
 * @Date 2020-08-10 17:30
 */
public class Consumer {
  public static void main(String[] args)throws  Exception {
    DefaultMQPushConsumer defaultMQPullConsumer =new DefaultMQPushConsumer("group1");
    defaultMQPullConsumer.setNamesrvAddr("120.78.86.102:9876;120.78.86.102:9877");
    defaultMQPullConsumer.subscribe("ordertopic", "order");

    //MessageListenerOrderl一个队列是用一个线程去消费
    defaultMQPullConsumer.registerMessageListener(new MessageListenerOrderly() {
      public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
        for (MessageExt msg : msgs) {
          System.out.println(Thread.currentThread().getId()+"消费者消费数据:"+new String(msg.getBody()));
        }
        return ConsumeOrderlyStatus.SUCCESS;
      }
    });
    defaultMQPullConsumer.start();
    System.out.println("消费者开始监听。。。。。");
  }

}
