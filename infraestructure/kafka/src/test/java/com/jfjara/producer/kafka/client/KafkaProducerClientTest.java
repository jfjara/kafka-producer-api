package com.jfjara.producer.kafka.client;

import com.jfjara.producer.dto.KafkaProducerMessageDto;
import com.jfjara.producer.kafka.factory.KafkaProducerFactory;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
    public void test_send_without_confirm_ok() {
        final KafkaProducer kafkaProducer = Mockito.mock(KafkaProducer.class);
        final Future futureResponse = Mockito.mock(Future.class);
        when(kafkaProducerFactory.getInstance()).thenReturn(kafkaProducer);
        when(kafkaProducer.send(any())).thenReturn(futureResponse);
        doNothing().when(kafkaProducer).close();
        kafkaProducerClient.sendWithoutConfirmation(KafkaProducerMessageDto.builder()
                .topic("aTopic")
                .key("aKey")
                .message("aMessage")
                .build());
        verify(kafkaProducerFactory, times(1)).getInstance();
        verify(kafkaProducer, times(1)).send(isA(ProducerRecord.class));
    }

    @Test
    public void test_send_with_confirm() throws ExecutionException, InterruptedException {
        final KafkaProducer kafkaProducer = Mockito.mock(KafkaProducer.class);
        final Future futureResponse = Mockito.mock(Future.class);
        TopicPartition topicPartition = new TopicPartition("test",1);
        RecordMetadata metadata =
                new RecordMetadata(topicPartition, 1,0,0,0, 0);
        when(kafkaProducerFactory.getInstance()).thenReturn(kafkaProducer);
        when(kafkaProducer.send(any())).thenReturn(futureResponse);
        when(futureResponse.get()).thenReturn(metadata);
       // when(metadata.offset()).thenReturn(1L);
        doNothing().when(kafkaProducer).close();
        Long offset = kafkaProducerClient.sendWithConfirmation(KafkaProducerMessageDto.builder()
                .topic("aTopic")
                .key("aKey")
                .message("aMessage")
                .build());
        Assertions.assertEquals(1L, offset);
        verify(kafkaProducerFactory, times(1)).getInstance();
        verify(futureResponse, times(1)).get();
    }

    @Test
    public void test_asynchronous_ok() {
        final KafkaProducer kafkaProducer = Mockito.mock(KafkaProducer.class);
        final Future futureResponse = Mockito.mock(Future.class);
        when(kafkaProducerFactory.getInstance()).thenReturn(kafkaProducer);
        when(kafkaProducer.send(any(), any())).thenReturn(futureResponse);
        doNothing().when(kafkaProducer).close();
        kafkaProducerClient.sendAsynchronous(KafkaProducerMessageDto.builder()
                .topic("aTopic")
                .key("aKey")
                .message("aMessage")
                .build());
        verify(kafkaProducerFactory, times(1)).getInstance();
        verify(kafkaProducer, times(1)).send(isA(ProducerRecord.class), isA(Callback.class));
    }


}
