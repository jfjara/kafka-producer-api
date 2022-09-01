package com.jfjara.producer.usecase;

import com.jfjara.producer.dto.KafkaProducerMessageDto;
import com.jfjara.producer.ports.ProducerRepository;

import java.util.concurrent.ExecutionException;

public class SendMessageWithConfirmationUseCase {

    private final ProducerRepository producerRepository;

    public SendMessageWithConfirmationUseCase(final ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public void execute(final KafkaProducerMessageDto kafkaProducerMessageDto)
            throws ExecutionException, InterruptedException {
        producerRepository.sendWithConfirmation(kafkaProducerMessageDto);
    }

}
