package com.liada.rabbitmq.ack;

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

		String exchangeName = "test_ack_exchange";
		String routingKey = "ack.#";
		String queueName = "test_ack_queue";
		// 声明交换机和队列
		channel.exchangeDeclare(exchangeName, "topic", true, false, null);
		channel.queueDeclare(queueName, false, false, false, null);
		channel.queueBind(queueName, exchangeName, routingKey);
		// 绑定队列到交换机上
		channel.basicConsume(queueName, false, new MyConsumer(channel));
	}
}
