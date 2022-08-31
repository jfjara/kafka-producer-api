package com.jfjara.producer.kafka.client;

import com.jfjara.producer.dto.KafkaProducerMessageDto;
import com.jfjara.producer.kafka.factory.KafkaProducerFactory;
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

    @Autowired
    private KafkaProducerFactory kafkaProducerFactory;

    public void send(final KafkaProducerMessageDto kafkaProducerMessageDto) {
        logger.info("Kafka producer send message {}", kafkaProducerMessageDto);
        final KafkaProducer<String, String> producer = kafkaProducerFactory.getInstance(producerConfig);
        producer.send(new ProducerRecord<>(kafkaProducerMessageDto.getTopic(),
                kafkaProducerMessageDto.getKey(),
                kafkaProducerMessageDto.getMessage()));
        producer.close();
    }


}
