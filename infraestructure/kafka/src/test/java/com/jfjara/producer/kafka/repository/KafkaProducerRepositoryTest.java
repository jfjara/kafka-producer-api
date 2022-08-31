package com.jfjara.producer.kafka.repository;

import com.jfjara.producer.kafka.client.KafkaProducerClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
public class KafkaProducerRepositoryTest {

    @Mock
    private KafkaProducerClient kafkaProducerClient;

    @InjectMocks
    private KafkaProducerRepository kafkaProducerRepository;

    @Test
    public void test_send() {
        Mockito.doNothing().when(kafkaProducerClient).send(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        kafkaProducerRepository.send("test-topic", "randomKey", "aMessage");
        Mockito.verify(kafkaProducerClient, Mockito.times(1)).send(Mockito.isA(String.class),
                Mockito.isA(String.class), Mockito.isA(String.class));
    }
}