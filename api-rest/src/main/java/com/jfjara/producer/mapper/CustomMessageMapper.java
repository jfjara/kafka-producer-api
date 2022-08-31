package com.jfjara.producer.mapper;

import com.jfjara.producer.api.model.CustomMessage;
import com.jfjara.producer.dto.KafkaProducerMessageDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomMessageMapper {

    KafkaProducerMessageDto toKafkaProducerMessageDto(final CustomMessage customMessage);

}
