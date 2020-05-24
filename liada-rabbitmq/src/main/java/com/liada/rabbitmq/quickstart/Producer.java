package com.liada.rabbitmq.quickstart;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author llq
 * @date 2020.05.15 15:56
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        // 获取到连接一级mq通道(相当于mysql获取连接)
        Connection connection = RabbitmqConnectionUtil.getConnection();
        // 从连接中创建通道(相当于mysql获取连接后还得获取到statement才能操作数据库一样的道理)
        Channel channel = connection.createChannel();

        Map<String, Object> headers = new HashMap<>();
        headers.put("my1", "111");
        headers.put("my2", "222");

        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2)
                .contentEncoding("UTF-8")
                .expiration("10000")
                .headers(headers)
                .build();

        // 声明exchangeName
        String exchangeName = "test_fanout_exchange";

        // 发送消息到交换机中
        String msg = "hello,rabbitmq!";
        channel.basicPublish("", "test_queue", properties, msg.getBytes());

        // 关闭通道和连接
        channel.close();
        connection.close();
    }
}
