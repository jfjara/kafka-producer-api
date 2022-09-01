package com.jfjara.producer.kafka.client;

import com.jfjara.producer.dto.KafkaProducerMessageDto;
import com.jfjara.producer.kafka.factory.KafkaProducerFactory;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class KafkaProducerClient {

    private static Logger logger = LoggerFactory.getLogger(KafkaProducerClient.class);

    @Autowired
    private KafkaProducerFactory kafkaProducerFactory;

    public Long sendWithConfirmation(final KafkaProducerMessageDto kafkaProducerMessageDto)
            throws ExecutionException, InterruptedException {
        logger.info("Kafka producer send with confirmation message {}", kafkaProducerMessageDto);
        final KafkaProducer<String, String> producer = kafkaProducerFactory.getInstance();
        Future<RecordMetadata> response = producer.send(new ProducerRecord<>(kafkaProducerMessageDto.getTopic(),
                kafkaProducerMessageDto.getKey(),
                kafkaProducerMessageDto.getMessage()));
        RecordMetadata recordMetadata = response.get();
        long offset = recordMetadata.offset();
        logger.info("Kafka producer sended with confirmation offset {} and message {}", offset, kafkaProducerMessageDto);
        producer.close();
        return offset;
    }

    public void sendWithoutConfirmation(final KafkaProducerMessageDto kafkaProducerMessageDto) {
        logger.info("Kafka producer send without confirmation message {}", kafkaProducerMessageDto);
        final KafkaProducer<String, String> producer = kafkaProducerFactory.getInstance();
        producer.send(new ProducerRecord<>(kafkaProducerMessageDto.getTopic(),
                kafkaProducerMessageDto.getKey(),
                kafkaProducerMessageDto.getMessage()));
        producer.close();
    }

    public void sendAsynchronous(final KafkaProducerMessageDto kafkaProducerMessageDto) {
        logger.info("Kafka producer send asynchronous message {}", kafkaProducerMessageDto);
        final KafkaProducer<String, String> producer = kafkaProducerFactory.getInstance();
        producer.send(new ProducerRecord<>(kafkaProducerMessageDto.getTopic(),
                kafkaProducerMessageDto.getKey(),
                kafkaProducerMessageDto.getMessage()), new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception exception) {
                if (exception != null) {
                    logger.error("kafka producer on completion received with exceptions {}",
                            exception.getMessage());
                } else {
                    logger.info("kafka producer on completion received correctly with offset {}",
                            recordMetadata.offset());
                }
            }
        });
        producer.close();
    }


}
