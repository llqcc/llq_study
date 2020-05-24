package com.liada.rabbitmq.myconsumer;

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
        System.err.println("consumerTag:" + consumerTag);
        System.err.println("envelope:" + envelope);
        System.err.println("properties:" + properties);
        System.err.println("body:" + new String(body));

        // 手动确认ACK
        /**
         * { deliveryTag（该消息的唯一标识）
         * multiple(是否批量，需要对应消费者中basicQos()中设置的是否每次处理的多条消息相对应)：true/false }
         */
        channel.basicAck(envelope.getDeliveryTag(), false);
        //note:如果这儿不进行手工签收的话，就不会消费下一条消息，起到了限流作用。
        //只有进行手工签收了才会消费下一条消息,没有确认在重启之后消息的条数也没有减少

    }

}
