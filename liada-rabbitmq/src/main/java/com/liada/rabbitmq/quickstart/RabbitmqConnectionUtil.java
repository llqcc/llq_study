package com.liada.rabbitmq.quickstart;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;

/**
 * 作用： rabbitmq工具类
 * 
 * @author Gavin
 * @time:2018年10月23日
 */
public class RabbitmqConnectionUtil {
	/**
	 * 作用： 获取rabbitmq连接工厂工具类
	 * 
	 * @Author:Gavin
	 * @time:2018年10月23日
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		// 定义连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		// 设置服务地址
		factory.setHost("192.168.1.16");
		// 设置端口号
		factory.setPort(5672);
		// 设置账号/密码/虚拟节点
		factory.setVirtualHost("/llqrabbit");
		factory.setUsername("llq");
		factory.setPassword("123456");
		// 如果网络不稳定会每隔3秒钟重新连接一下
		factory.setAutomaticRecoveryEnabled(true);
		factory.setNetworkRecoveryInterval(3000);
		// 通过工厂获取连接
		Connection connection = (Connection) factory.newConnection();
		return connection;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getConnection());
	}
}
