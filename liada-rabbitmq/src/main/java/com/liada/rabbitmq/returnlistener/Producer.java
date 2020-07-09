package com.liada.rabbitmq.returnlistener;

import com.liada.rabbitmq.quickstart.RabbitmqConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
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

        //指定消息投递模式：消息的确认消息
        channel.confirmSelect();

        // 声明exchangeName
        String exchangeName = "test.topic";
        String routingKey = "user.save";
        String routingKeyError = "error.save";

        // 添加一个确认监听
        channel.addConfirmListener(new ConfirmListener() {
            // 消息失败发送失败会进入到这个方法
            /**
             * 业务处理的时候这里就可以处理一些失败的消息后的逻辑,如：日志记录，错误跟踪等
             *{ deliveryTag:这条消息的唯一标签
             *  multiple :是否批量
             *  }
             */


            // 消息发送成功后会进入到这个方法
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                // TODO Auto-generated method stub
                System.err.println("------- ack -------");
            }

            /**
             * 在一下这些情况下会走handleNack方法：
             * 磁盘不够用，队列满了等等。
             *
             * 该模式不能确认消息的100%可靠性传递，有时候可能会出现网络中断导致消息消息的情况，
             * 这时候需要采用一些定时任务去抓取一些消息重新进行投递。
             */
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                // TODO Auto-generated method stub
                System.err.println("------no ack-------");
            }
        });

        // 发送一条消息
        /**
         * Mandatory:需要进行消息不可达的监听， 如果消息不可达就会把该消息进行返回， 不可达的因素：路由key找不到，等
         * Mandatory如果为false时候：消息没有路由到就会自动删除，
         * 如果为true时候，没有路由到就不会删除
         */
        // 添加return的监听
        channel.addReturnListener(new ReturnListener() {
            /**
             * { replyCode:返回消息码， replyText:返回消息体， routingKey:返回消息路由键, }
             */
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey,
                                     AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.err.println("-----handle return-----");
                System.err.println("replyCode:" + replyCode);
                System.err.println("replyText:" + replyText);
                System.err.println("exchange:" + exchange);
                System.err.println("routingKey:" + routingKey);
                System.err.println("properties:" + properties);
                System.err.println("body:" + new String(body));

            }
        });
        // 发送成功路由的消息
//        channel.basicPublish(exchangeName, routingKey, true, null, "Hello return msg".getBytes());
        //发送失败路由的消息
        channel.basicPublish(exchangeName, routingKey, true, null, "Hello return msg".getBytes());
        /**
         * Mandatory为false不会进行监听
         */
        //channel.basicPublish(exchangeName, routingKeyError, false, null, "Hello return msg".getBytes());
        // 关闭通道和连接
        // channel.close();
        // connection.close();
    }
}
