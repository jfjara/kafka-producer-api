package com.jfjara.producer.usecase;

import com.jfjara.producer.dto.KafkaProducerMessageDto;
import com.jfjara.producer.ports.ProducerRepository;

public class SendDataUseCase {
    private final ProducerRepository producerRepository;

    public SendDataUseCase(final ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public void execute(final KafkaProducerMessageDto kafkaProducerMessageDto) {
        producerRepository.send(kafkaProducerMessageDto);
    }

}
