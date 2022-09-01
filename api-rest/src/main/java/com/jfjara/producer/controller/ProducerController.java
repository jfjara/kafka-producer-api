package com.jfjara.producer.controller;

import com.jfjara.producer.api.controller.SendApi;
import com.jfjara.producer.api.model.CustomMessage;
import com.jfjara.producer.dto.KafkaProducerMessageDto;
import com.jfjara.producer.mapper.CustomMessageMapper;
import com.jfjara.producer.usecase.SendMessageAsynchronousUseCase;
import com.jfjara.producer.usecase.SendMessageWithConfirmationUseCase;
import com.jfjara.producer.usecase.SendMessageWithoutConfirmationUseCase;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

@RestController
public class ProducerController implements SendApi {

    @Autowired
    private SendMessageWithoutConfirmationUseCase sendMessageWithoutConfirmationUseCase;

    @Autowired
    private SendMessageWithConfirmationUseCase sendMessageWithConfirmationUseCase;

    @Autowired
    private SendMessageAsynchronousUseCase sendMessageAsynchronousUseCase;

    @Autowired
    private CustomMessageMapper customMessageMapper;

    @Override
    public ResponseEntity<Void> sendWithoutConfirm(final CustomMessage customMessage) {
        final KafkaProducerMessageDto kafkaProducerMessageDto = customMessageMapper
                .toKafkaProducerMessageDto(customMessage);
        sendMessageWithoutConfirmationUseCase.execute(kafkaProducerMessageDto);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Long> sendWithConfirm(final CustomMessage customMessage) {
        final KafkaProducerMessageDto kafkaProducerMessageDto = customMessageMapper
                .toKafkaProducerMessageDto(customMessage);
        try {
            sendMessageWithConfirmationUseCase.execute(kafkaProducerMessageDto);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> sendAsynchronous(final CustomMessage customMessage) {
        final KafkaProducerMessageDto kafkaProducerMessageDto = customMessageMapper
                .toKafkaProducerMessageDto(customMessage);
        sendMessageAsynchronousUseCase.execute(kafkaProducerMessageDto);
        return ResponseEntity.ok(null);
    }


}
