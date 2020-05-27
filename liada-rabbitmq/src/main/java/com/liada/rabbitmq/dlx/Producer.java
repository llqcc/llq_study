package com.liada.rabbitmq.dlx;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
	/**
	 * 作用： DLX死信队列
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

		// 4.制定我们的消息投递模式：消息的确认模式
		channel.confirmSelect();

		String exchangeName = "test_dlx_exchange";
		String routingKey = "dlx.save";
		AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
				.deliveryMode(2)// 2.表示持久化消息，1代表不持久化
				.contentEncoding("UTF-8")// 设置字符编码
				.expiration("10000")// 设置该消息的过期时间（单位毫秒），如果在该时间段内消息没有被消费掉那么就会被清除
				.build();
		// 发送一条死信消息（这儿就是时间过期后就是死信消息了）
		channel.basicPublish(exchangeName, routingKey, true, properties, "Hello dlx msg".getBytes());
		 channel.close();
		 connection.close();
	}
}
