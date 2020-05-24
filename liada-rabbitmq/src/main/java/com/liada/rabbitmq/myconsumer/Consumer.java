package com.liada.rabbitmq.myconsumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;

public class Consumer {
    public static void main(String[] args) throws Exception, ConsumerCancelledException, InterruptedException {
        // 1.创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置服务地址
        factory.setHost("192.168.1.16");
        // 设置端口号
        factory.setPort(5672);
        // 设置账号/密码/虚拟节点
        factory.setVirtualHost("/llqrabbit");
        factory.setUsername("llq");
        factory.setPassword("123456");
        // 2.获取到连接对象Connection
        Connection connection = factory.newConnection();

        // 3.通过Connection创建一个新的Channel
        Channel channel = connection.createChannel();

        String exchangeName = "test_qos_exchange";
        String routingKey = "qos.#";
        String queueName = "test_qos_queue";
        // 声明交换机和队列
        channel.exchangeDeclare(exchangeName, "topic", true, false, null);
        channel.queueDeclare(queueName, false, false, false, null);
        // 绑定队列到交换机上
        channel.queueBind(queueName, exchangeName, routingKey);

        // 如果要使用限流方式，第一件事设置autoAck为false为手动签收
        /**
         * {
         * prefetchSize（消息大小）：0（不限制大小），
         * prefetchCount（消息的条数）：int(每次消费多少条消息，设为1就可以了),
         * global：false(consumer)/true(channel)
         * }
         */
        channel.basicQos(0, 2, false);
        channel.basicConsume(queueName, false, new MyConsumer(channel));
    }
}
