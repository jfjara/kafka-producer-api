package com.jfjara.producer.repository;

public interface ProducerRepository {

    void send(final String topic, final String key, final String value);

}
