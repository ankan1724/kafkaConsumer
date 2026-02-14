package com.kafka.consumer.kafkaConsumer.service;

import java.util.Map;

public class MessageWrapper {
    private PaymentMessages payload;
    private Map<String, Object> headers;

    public PaymentMessages getPayload() {
        return payload;
    }

    public void setPayload(PaymentMessages payload) {
        this.payload = payload;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }
}
