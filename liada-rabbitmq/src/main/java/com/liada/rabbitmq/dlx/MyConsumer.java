package com.liada.rabbitmq.dlx;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * 作用： 自定义消费者
 * 
 * @author Gavin
 * @time:2018年10月30日
 */
public class MyConsumer extends DefaultConsumer {

	public MyConsumer(Channel channel) {
		super(channel);
	}

	@Override
	public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
			throws IOException {

		System.err.println("-------自定义消费端-----");
		System.err.println("consumerTag:" + consumerTag);
		System.err.println("envelope:" + envelope);
		System.err.println("properties:" + properties);
		System.err.println("body:" + new String(body));
	}

}
