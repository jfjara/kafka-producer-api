package com.jfjara.producer.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class KafkaProducerMessageDto {

    private String topic;
    private String key;
    private String message;

}
