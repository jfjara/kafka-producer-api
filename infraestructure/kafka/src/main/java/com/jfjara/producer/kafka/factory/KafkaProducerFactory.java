package com.jfjara.producer.kafka.factory;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KafkaProducerFactory {

    public KafkaProducer<String, String> getInstance(final Properties kafkaProperties) {
        return new KafkaProducer<>(kafkaProperties);
    }

}
