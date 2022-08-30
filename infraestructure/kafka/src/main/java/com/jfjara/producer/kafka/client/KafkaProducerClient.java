package com.jfjara.producer.kafka.client;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class KafkaProducerClient {

    private static Logger logger = LoggerFactory.getLogger(KafkaProducerClient.class);

    @Autowired
    @Qualifier("producerConfig")
    private Properties producerConfig;

    public void send(final String topic, final String key, final String value) {
        logger.info("Kafka producer send to topic {} key {} value {}", topic, key, value);
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(producerConfig);
        producer.send(new ProducerRecord<>(topic, key, value));
        producer.close();
    }


}
