package com.jfjara.producer.kafka.repository;

import com.jfjara.producer.kafka.client.KafkaProducerClient;
import com.jfjara.producer.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerRepository implements ProducerRepository {

    @Autowired
    private KafkaProducerClient kafkaProducerClient;

    @Override
    public void send(final String topic, final String key, final String value) {
        kafkaProducerClient.send(topic, key, value);
    }
}
