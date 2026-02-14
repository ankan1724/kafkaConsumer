package com.kafka.consumer.kafkaConsumer;

import com.kafka.consumer.kafkaConsumer.service.KafkaSSLProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableConfigurationProperties(KafkaSSLProperties.class)
@EnableKafkaStreams
public class KafkaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);


	}

}
