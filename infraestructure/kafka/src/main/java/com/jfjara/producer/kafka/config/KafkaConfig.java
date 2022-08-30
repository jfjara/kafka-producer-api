package com.jfjara.producer.kafka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    private static Logger logger = LoggerFactory.getLogger(KafkaConfig.class);

    @Value("${producer.config.servers}")
    private String servers;

    @Bean("producerConfig")
    public Properties producerConfig() {
        logger.info("===========> Start to configure KAFKA module infraestructure");
        Properties properties = new Properties();
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 0:   el productor no espera a la confirmacion del broker. Intuimos que se ha escrito el dato.
        //      Util cuando no nos importa perder algun dato, ej: metricas de clicks en una web
        // 1:   Productor espera confirmacion cuando la replica lider recibe el mensaje.
        //      Si no se recibe, devuelve el error y podremos reintentarlo. Evita perdida de datos
        //      En caso de error y no mas replicas -> perdida de datos
        // all: Obtiene confirmacion cuando todas las replicas reciben el mensaje
        properties.put("acks", "all");

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("buffer.memory", 33554432);

        logger.info("===========> End to configure KAFKA module infraestructure");

        return properties;
    }


}
