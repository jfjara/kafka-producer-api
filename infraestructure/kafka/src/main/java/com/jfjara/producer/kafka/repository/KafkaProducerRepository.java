package com.jfjara.producer.kafka.repository;

import com.jfjara.producer.dto.KafkaProducerMessageDto;
import com.jfjara.producer.kafka.client.KafkaProducerClient;
import com.jfjara.producer.ports.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class KafkaProducerRepository implements ProducerRepository {

    @Autowired
    private KafkaProducerClient kafkaProducerClient;

    @Override
    public void sendWithoutConfirmation(final KafkaProducerMessageDto kafkaProducerMessageDto) {
        kafkaProducerClient.sendWithoutConfirmation(kafkaProducerMessageDto);
    }

    @Override
    public Long sendWithConfirmation(KafkaProducerMessageDto kafkaProducerMessageDto)
            throws ExecutionException, InterruptedException {
        return kafkaProducerClient.sendWithConfirmation(kafkaProducerMessageDto);
    }

    @Override
    public void sendAsynchronous(KafkaProducerMessageDto kafkaProducerMessageDto) {
        kafkaProducerClient.sendAsynchronous(kafkaProducerMessageDto);
    }
}
