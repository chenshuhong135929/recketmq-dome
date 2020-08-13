package com.recketmq.transaction.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @Auther ChenShuHong
 * @Date 2020-08-10 14:14
 * 消息消费者
 */
public class Consumer {
  public static void main(String[] args) throws MQClientException {
    DefaultMQPushConsumer defaultMQPullConsumer =new DefaultMQPushConsumer("group1" );
    defaultMQPullConsumer.setNamesrvAddr("120.78.86.102:9876;120.78.86.102:9877");
    defaultMQPullConsumer.subscribe("transation", "Tag11");

    defaultMQPullConsumer.registerMessageListener(new MessageListenerConcurrently() {

      public ConsumeConcurrentlyStatus consumeMessage(
          List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        for (MessageExt msg : msgs) {
          System.out.println("消费者消费数据:"+new String(msg.getBody()));
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
      }
    });
    defaultMQPullConsumer.start();
    System.out.println("消费者开始监听。。。。。");
  }

}
