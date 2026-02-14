package com.kafka.consumer.kafkaConsumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Service;

@Service


// removing this as this is normal consumer..
public class Consumer {
//    private static String inputTopic="payment_EOD_data";
//    private static String outputTopic="payment_successfull_EOD_data";
//
//    Logger log= LoggerFactory.getLogger(this.getClass());
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @KafkaListener(topics = "payment_EOD_data", groupId = "payment-group")
//    public void consume(String message) {
//        try {
//            // manually Deserialize the JSON string into PaymentMessages object
//            MessageWrapper wrapper = objectMapper.readValue(message, MessageWrapper.class);
//            PaymentMessages messages=wrapper.getPayload();
//            log.info("Received this message: {}",messages.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
