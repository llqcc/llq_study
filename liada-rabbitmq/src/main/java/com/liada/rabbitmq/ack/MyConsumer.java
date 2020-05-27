package com.liada.rabbitmq.ack;

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
	private Channel channel;

	public MyConsumer(Channel channel) {
		super(channel);
		this.channel = channel;
	}

	@Override
	public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
			throws IOException {

		System.err.println("-------自定义消费端-----");
		System.err.println("body:" + new String(body));
		if((Integer)(properties.getHeaders().get("num"))==0) {
			/**
			 * {
			 * requeue（是否重回队列）:true/false
			 * }
			 * 如果是重回队列，就会把消息重新添加到Broker的尾部,然后再带着那些没有成功的消息重复投递
			 * 实际生产环境中一般为false（因为重回队列再拿来消费一样的会失败，这儿设为false通过日志记录来处理这个失败的情况更好）
			 */
			//失败时候Nack
			channel.basicNack(envelope.getDeliveryTag(), false, true);
		}else {
			//成功时候ACK
			channel.basicAck(envelope.getDeliveryTag(), false);
		}
	}

}
