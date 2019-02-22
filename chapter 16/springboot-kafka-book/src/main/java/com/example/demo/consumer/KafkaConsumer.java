package com.example.demo.consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 描述：消费者类
 * @author ay
 * @date 2019-01-17
 */
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"spring-kafka-topic"}, groupId = "spring-kafka-consumer")
    public void comsumer(ConsumerRecord<?, ?> record) {
        System.out.println("consumer message:" + record.value());
    }

}
