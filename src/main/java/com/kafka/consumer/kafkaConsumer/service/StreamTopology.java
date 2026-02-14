package com.kafka.consumer.kafkaConsumer.service;


import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;


@Component
public class StreamTopology {

    private static final String inputTopic = "payment_EOD_data";
    private static final String outputTopic = "payment_successfully_EOD_data";
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Bean
    public KStream<String, PaymentMessages> paymentEODStream(StreamsBuilder builder,JsonSerde<PaymentMessages> paymentSerde,
                                                             IncomingMessageTransformationBean messageTransformationBean) {

        KStream<String, PaymentMessages> inputStream = builder.stream(inputTopic, Consumed.with(Serdes.String(), paymentSerde));

        inputStream.peek((key, value) -> log.info("📥 Received from topic: key={}, value={}", key, value));

        KStream<String, PaymentMessages> outputStream = inputStream.filter((key, payment) ->messageTransformationBean.

        outputStream.peek((key, value) -> log.info("✅ SUCCESSFUL payment: {}", value));

        outputStream.to(outputTopic, Produced.with(Serdes.String(),paymentSerde));

        return outputStream;
    }
}
