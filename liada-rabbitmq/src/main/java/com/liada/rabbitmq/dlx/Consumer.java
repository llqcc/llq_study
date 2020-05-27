package com.liada.rabbitmq.dlx;

import java.util.HashMap;
import java.util.Map;

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

		String exchangeName = "test_dlx_exchange";
		String routingKey = "dlx.#";
		String queueName = "test_dlx_queue";
		// 声明交换机和队列
		channel.exchangeDeclare(exchangeName, "topic", true, false, null);
		/**
		 * 死信队列
		 */
		Map<String, Object> agruments = new HashMap<String, Object>();
		agruments.put("x-dead-letter-exchange", "dlx.exchange");//dlx.exchange是出现了死信队列后要路由到的交换机名字
		// 这个agruments属性，要设置到声明的队列上才能进行死信监听
		channel.queueDeclare(queueName, false, false, false, agruments);
		// 绑定队列到交换机上
		channel.queueBind(queueName, exchangeName, routingKey);

		/**
		 * 声明死信队列(死信队列就是个正常的交换机)
		 * 绑定这个死信队列交换机的队列
		 */
		channel.exchangeDeclare("dlx.exchange", "topic", true, false, null);
		channel.queueDeclare("dlx.queue", false, false, false, null);
		channel.queueBind("dlx.queue", "dlx.exchange", "#");
		
		// 监听队列,自动签收
		channel.basicConsume(queueName, true, new MyConsumer(channel));
	}
}
