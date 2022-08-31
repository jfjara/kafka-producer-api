package com.jfjara.producer.controller;

import com.jfjara.producer.api.controller.SendApi;
import com.jfjara.producer.api.controller.SendApiController;
import com.jfjara.producer.api.controller.SendApiDelegate;
import com.jfjara.producer.api.model.CustomMessage;
import com.jfjara.producer.dto.KafkaProducerMessageDto;
import com.jfjara.producer.mapper.CustomMessageMapper;
import com.jfjara.producer.usecase.SendDataUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ProducerController implements SendApi {

    @Autowired
    private SendDataUseCase sendDataUseCase;

    @Autowired
    private CustomMessageMapper customMessageMapper;

    @Override
    public ResponseEntity<Void> send(final CustomMessage customMessage) {
        final KafkaProducerMessageDto kafkaProducerMessageDto = customMessageMapper
                .toKafkaProducerMessageDto(customMessage);
        sendDataUseCase.execute(kafkaProducerMessageDto);
        return ResponseEntity.ok(null);
    }
}
