package com.recketmq.batch.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @Auther ChenShuHong
 * @Date 2020-08-10 14:14
 * 消息消费者
 */
public class Consumer {
  public static void main(String[] args) throws MQClientException {
    DefaultMQPushConsumer defaultMQPullConsumer =new DefaultMQPushConsumer("group1");
    defaultMQPullConsumer.setNamesrvAddr("120.78.86.102:9876;120.78.86.102:9877");
    defaultMQPullConsumer.subscribe("BatchTest", "Tag");
    //广播模式MessageModel.BROADCASTING 默认是负载均衡模式，不需要写
    defaultMQPullConsumer.setMessageModel(MessageModel.BROADCASTING);
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
  }

}
