package com.jfjara.producer.kafka.client;

import com.jfjara.producer.kafka.factory.KafkaProducerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.ProducerRecord;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KafkaProducerClientTest {

    @Mock
    private Properties producerConfig;

    @Mock
    private KafkaProducerFactory kafkaProducerFactory;

    @InjectMocks
    private KafkaProducerClient kafkaProducerClient;

    @Test
    public void test_send_ok() {
        final KafkaProducer kafkaProducer = Mockito.mock(KafkaProducer.class);
        final Future futureResponse = Mockito.mock(Future.class);
        when(kafkaProducerFactory.getInstance(any())).thenReturn(kafkaProducer);
        when(kafkaProducer.send(any())).thenReturn(futureResponse);
        doNothing().when(kafkaProducer).close();
        kafkaProducerClient.send("aTopic", "aKey", "aMessage");
        verify(kafkaProducerFactory, times(1)).getInstance(isA(Properties.class));
        verify(kafkaProducer, times(1)).send(isA(ProducerRecord.class));
    }
}
