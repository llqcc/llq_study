package com.liada.rabbitmq.returnlistener;

import com.liada.rabbitmq.quickstart.RabbitmqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

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

        String exchangeName = "test_topic";
        String routingKey = "user.#";
        String queueName = "test_topic_queue";
        // 声明交换机和队列
        channel.exchangeDeclare(exchangeName, "topic", true, false, null);
        channel.queueDeclare(queueName, false, false, false, null);
        // 绑定队列到交换机上
        channel.queueBind(queueName, exchangeName, routingKey);

        // 创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        // 绑定队列消费者
        channel.basicConsume(queueName, true, queueingConsumer);

        while (true) {
            //7 获取消息
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.err.println("消费端: " + msg);
        }


    }
}
