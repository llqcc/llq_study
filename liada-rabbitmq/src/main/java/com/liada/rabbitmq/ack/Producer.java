package com.liada.rabbitmq.ack;

import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
	/**
	 * 作用： Return Listener
	 * 
	 * @Author:Gavin
	 * @time:2018年10月30日
	 * @param args
	 * @throws Exception
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

		String exchangeName = "test_ack_exchange";
		String routingKey = "ack.save";
		for (int i = 0; i < 3; i++) {
			Map<String, Object> headers = new HashMap<String, Object>();
			headers.put("num", i);
			AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().deliveryMode(2)
					.contentEncoding("UTF-8").headers(headers).build();
			String msg = "Hello qos msg" + i;
			// 发送消息
			channel.basicPublish(exchangeName, routingKey, true, properties, msg.getBytes());
		}
		channel.close();
		connection.close();
	}
}
