package com.kafka.consumer.kafkaConsumer.service;


import org.apache.commons.logging.Log;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;


@Component
public class StreamTopology {

    Logger log= LoggerFactory.getLogger(this.getClass());
    private static final String inputTopic = "payment_EOD_data";
    private static final String outputTopic = "payment_successfull_EOD_data";


    @Bean
    public KStream<String, PaymentMessages> paymentEODStream(StreamsBuilder streamBuilder) throws Throwable {
try{
        JsonDeserializer<Message<PaymentMessages>> messageDeserializer = new JsonDeserializer<>(Message.class);
        JsonSerde<Message<PaymentMessages>> messageSerde = new JsonSerde<>(Message.class);

        KStream<String, Message<PaymentMessages>> inputStream = streamBuilder.stream(inputTopic, Consumed.with(Serdes.String(), messageSerde));
        KStream<String, PaymentMessages> outputStream = inputStream
                .mapValues(message -> (PaymentMessages) message.getPayload())
                .filter((key, payment) -> Status.SUCCESSFUL.equals(payment.getStatus()));

        outputStream.to(outputTopic, Produced.with(Serdes.String(), new JsonSerde<>(PaymentMessages.class)));
        log.info("Messages sent to topic");
        return outputStream;
        } catch (Exception e) {
            throw e.getCause();
        }
    }
}
