package com.jfjara.producer.ports;

import com.jfjara.producer.dto.KafkaProducerMessageDto;

import java.util.concurrent.ExecutionException;

public interface ProducerRepository {

    void sendWithoutConfirmation(final KafkaProducerMessageDto kafkaProducerMessageDto);
    Long sendWithConfirmation(final KafkaProducerMessageDto kafkaProducerMessageDto) throws ExecutionException, InterruptedException;
    void sendAsynchronous(final KafkaProducerMessageDto kafkaProducerMessageDto);

}
