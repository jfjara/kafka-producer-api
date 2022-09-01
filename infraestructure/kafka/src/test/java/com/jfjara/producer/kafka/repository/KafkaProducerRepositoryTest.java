package com.jfjara.producer.kafka.repository;

import com.jfjara.producer.dto.KafkaProducerMessageDto;
import com.jfjara.producer.kafka.client.KafkaProducerClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ExecutionException;

@ExtendWith(MockitoExtension.class)
public class KafkaProducerRepositoryTest {

    @Mock
    private KafkaProducerClient kafkaProducerClient;

    @InjectMocks
    private KafkaProducerRepository kafkaProducerRepository;

    @Test
    public void test_send_without_confirmation_ok() {
        Mockito.doNothing().when(kafkaProducerClient).sendWithoutConfirmation(Mockito.any());
        kafkaProducerRepository.sendWithoutConfirmation(KafkaProducerMessageDto.builder()
                        .topic("aTopic")
                        .key("aKey")
                        .message("aMessage")
                        .build());
        Mockito.verify(kafkaProducerClient, Mockito.times(1))
                .sendWithoutConfirmation(Mockito.isA(KafkaProducerMessageDto.class));
    }

    @Test
    public void test_send_with_confirmation_ok() throws ExecutionException, InterruptedException {
        Mockito.when(kafkaProducerClient.sendWithConfirmation(Mockito.any())).thenReturn(1L);
        kafkaProducerRepository.sendWithConfirmation(KafkaProducerMessageDto.builder()
                .topic("aTopic")
                .key("aKey")
                .message("aMessage")
                .build());
        Mockito.verify(kafkaProducerClient, Mockito.times(1))
                .sendWithConfirmation(Mockito.isA(KafkaProducerMessageDto.class));
    }

    @Test
    public void test_send_asynchronous_ok() {
        Mockito.doNothing().when(kafkaProducerClient).sendAsynchronous(Mockito.any());
        kafkaProducerRepository.sendAsynchronous(KafkaProducerMessageDto.builder()
                .topic("aTopic")
                .key("aKey")
                .message("aMessage")
                .build());
        Mockito.verify(kafkaProducerClient, Mockito.times(1))
                .sendAsynchronous(Mockito.isA(KafkaProducerMessageDto.class));
    }
}
