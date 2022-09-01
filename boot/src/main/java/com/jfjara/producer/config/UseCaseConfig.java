package com.jfjara.producer.config;

import com.jfjara.producer.kafka.repository.KafkaProducerRepository;
import com.jfjara.producer.usecase.SendMessageAsynchronousUseCase;
import com.jfjara.producer.usecase.SendMessageWithConfirmationUseCase;
import com.jfjara.producer.usecase.SendMessageWithoutConfirmationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public SendMessageWithoutConfirmationUseCase sendMessageWithoutConfirmationUseCase(final KafkaProducerRepository kafkaProducerRepository) {
        return new SendMessageWithoutConfirmationUseCase(kafkaProducerRepository);
    }

    @Bean
    public SendMessageWithConfirmationUseCase sendMessageWithConfirmationUseCase(final KafkaProducerRepository kafkaProducerRepository) {
        return new SendMessageWithConfirmationUseCase(kafkaProducerRepository);
    }

    @Bean
    public SendMessageAsynchronousUseCase sendMessageAsynchronousUseCase(final KafkaProducerRepository kafkaProducerRepository) {
        return new SendMessageAsynchronousUseCase(kafkaProducerRepository);
    }


}
