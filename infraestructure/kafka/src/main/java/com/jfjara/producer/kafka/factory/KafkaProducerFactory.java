package com.jfjara.producer.kafka.factory;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KafkaProducerFactory {
    @Qualifier("producerConfig")
    @Autowired
    private Properties kafkaProperties;

    public KafkaProducer<String, String> getInstance() {
        return new KafkaProducer<>(kafkaProperties);
    }

}
