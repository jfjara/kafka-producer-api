package com.jfjara.producer.config;

import com.jfjara.producer.kafka.repository.KafkaProducerRepository;
import com.jfjara.producer.usecase.SendDataUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public SendDataUseCase sendDataUseCase(final KafkaProducerRepository kafkaProducerRepository) {
        return new SendDataUseCase(kafkaProducerRepository);
    }


}
