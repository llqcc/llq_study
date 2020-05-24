package com.liada.rabbitmq.quickstart;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Map;

/**
 * @Description
 * @Author llq
 * @date 2020.05.15 15:55
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        // 获取到连接一级mq通道(相当于mysql获取连接)
        Connection connection = RabbitmqConnectionUtil.getConnection();
        // 从连接中创建通道(相当于mysql获取连接后还得获取到statement才能操作数据库一样的道理)
        Channel channel = connection.createChannel();

        String exchangeName = "test_fanout_exchange";
        String exchangeType = "fanout";
        String queueName = "test_queue";
        String routingKey = "bnbn";

        // 声明exchangeName
        //channel.exchangeDeclare(exchangeName, exchangeType, true,false,false,null);

        // 声明（创建）队列【相当于mysql数据库中的表，用来存数据】(用来接受消息，生产者---》发送消息到队列《------消费者消费消息)
        channel.queueDeclare(queueName, false, false, false, null);

        // 绑定队列到交换机
        //channel.queueBind(queueName, exchangeName, routingKey);

        // 创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        // 绑定队列消费者
        channel.basicConsume(queueName, true, queueingConsumer);

        while(true){
            //7 获取消息
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.err.println("消费端: " + msg);
            Map<String, Object> headers = delivery.getProperties().getHeaders();
            System.err.println("headers get my1 value: " + headers.get("my1"));
            //Envelope envelope = delivery.getEnvelope();
        }

    }
}
