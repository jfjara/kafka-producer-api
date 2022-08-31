package com.jfjara.producer.ports;

import com.jfjara.producer.dto.KafkaProducerMessageDto;

public interface ProducerRepository {

    void send(final KafkaProducerMessageDto kafkaProducerMessageDto);

}
