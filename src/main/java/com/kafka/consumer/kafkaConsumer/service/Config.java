package com.kafka.consumer.kafkaConsumer.service;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Config {

    @Bean
    public ConsumerFactory<String, PaymentMessages> messageConsumerFactory(KafkaSSLProperties props) {

        try {
            Map<String, Object> config = new HashMap<>();
            config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, props.getBootstrapServers());
            config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
            config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
            config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.kafka.consumer.kafkaConsumer.service.PaymentMessages");
            config.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false); // avoid type header issues

            config.put("security.protocol", props.getSecurityProtocol());
            config.put("ssl.truststore.location", props.getTruststoreLocation());
            config.put("ssl.truststore.password", props.getTruststorePassword());
            config.put("ssl.keystore.location", props.getKeystoreLocation());
            config.put("ssl.keystore.password", props.getKeystorePassword());
            config.put("ssl.key.password", props.getKeyPassword());
            config.put("ssl.endpoint.identification.algorithm", "");

            return new DefaultKafkaConsumerFactory<>(config);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }



    }
    @Bean
    public JsonSerde<PaymentMessages> paymentSerde() {

        JsonDeserializer<PaymentMessages> deserializer =
                new JsonDeserializer<>(PaymentMessages.class,false);

        deserializer.addTrustedPackages("*");

        JsonSerializer<PaymentMessages> serializer =
                new JsonSerializer<>();

        return new JsonSerde<>(serializer, deserializer);
    }

}
