package com.jfjara.producer.usecase;

import com.jfjara.producer.repository.ProducerRepository;

public class SendDataUseCase {
    private final ProducerRepository producerRepository;

    public SendDataUseCase(final ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public void execute(final String topic, final String key, final String value) {
        producerRepository.send(topic, key, value);
    }

}
