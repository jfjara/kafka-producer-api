package com.jfjara.producer.usecase;

import com.jfjara.producer.dto.KafkaProducerMessageDto;
import com.jfjara.producer.ports.ProducerRepository;

import java.util.concurrent.ExecutionException;

public class SendMessageAsynchronousUseCase {

    private final ProducerRepository producerRepository;

    public SendMessageAsynchronousUseCase(final ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public void execute(final KafkaProducerMessageDto kafkaProducerMessageDto) {
        producerRepository.sendAsynchronous(kafkaProducerMessageDto);
    }

}
