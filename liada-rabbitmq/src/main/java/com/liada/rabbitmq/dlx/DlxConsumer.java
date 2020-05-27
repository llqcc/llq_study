package com.liada.rabbitmq.dlx;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * 作用： 死信队列的消费者，对死信消息进行消费
 * 
 * @author Gavin
 * @time:2018年12月4日
 */
public class DlxConsumer {
	public static void main(String[] args) throws IOException, TimeoutException {
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

		Channel channel = connection.createChannel();

		String exchangeName = "dlx.exchange";
		String routingKey = "#";
		String queueName = "dlx.queue";
		// 声明队列和交换机
		channel.exchangeDeclare(exchangeName, "topic", true, false, null);
		channel.queueDeclare(queueName, false, false, false, null);
		channel.queueBind(queueName, exchangeName, routingKey);

		channel.basicQos(1);

		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				super.handleDelivery(consumerTag, envelope, properties, body);
				String message = new String(body, "UTF-8");
				System.out.println("DLX queue'" + message + "'");
			}
		};

		channel.basicConsume(queueName, true, consumer);
	}
}
