package com.recketmq.base.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @Auther ChenShuHong
 * @Date 2020-08-10 14:07
 * 单向发送消息
 */
public class OneWayProducer {
  public static void main(String[] args) throws Exception {
    DefaultMQProducer producer = new DefaultMQProducer("group1");
    producer.setNamesrvAddr("120.78.86.102:9876;120.78.86.102:9877");
    producer.start();
    for (int i =0;i<10;i++){
      //主题 topic 消息tag 消息内容
      Message msg = new Message("base","Tag3",("Holledaoxing  chenshuhong"+i).getBytes());
       producer.sendOneway(msg);
      Thread.sleep(1000);
    }
    producer.shutdown();
  }
}
