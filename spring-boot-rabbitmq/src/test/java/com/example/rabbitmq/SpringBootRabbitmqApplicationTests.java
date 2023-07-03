package com.example.rabbitmq;

import com.example.rabbitmq.config.RabbitConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringBootRabbitmqApplication.class)
class SpringBootRabbitmqApplicationTests {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Test
    void sendPersistentMsg() {
        MessageProperties messageProperties = new MessageProperties();
        // 设置持久化投递模式
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        Message rabbitMessage = MessageBuilder
                .withBody("发送一条消息".getBytes())
                .andProperties(messageProperties)
                .build();
    
        rabbitTemplate.send(RabbitConfig.DIRECT_EXCHANGE, RabbitConfig.DIRECT_ROUTING_KEY, rabbitMessage);
    }
    
}
