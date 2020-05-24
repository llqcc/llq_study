package com.liada.rabbitmq.myconsumer;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ReturnListener;
import com.rabbitmq.client.AMQP.BasicProperties;

public class Producer {
    /**
     * 作用： Return Listener
     *
     * @param args
     * @throws Exception
     * @Author:Gavin
     * @time:2018年10月30日
     */
    public static void main(String[] args) throws Exception {
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
        String routingKey = "qos.save";
        for (int i = 0; i < 3; i++) {
            // 发送消息
            channel.basicPublish(exchangeName, routingKey, true, null, "Hello qos msg".getBytes());
        }
        channel.close();
        connection.close();
    }
}
