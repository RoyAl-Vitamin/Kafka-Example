package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

/**
 * По расписанию отправляет в топик "clear" с ключом "KEY" раз в 3 секунды
 */
@Component
public class SendMessageTask {

    private final Logger logger = LoggerFactory.getLogger(SendMessageTask.class);

    private final Producer producer;

    private final String TOPIC_NAME = "ttopic";

    private final String KEY = "KEY";

    public SendMessageTask(Producer producer) {
        this.producer = producer;
    }

    @Scheduled(fixedRateString = "3000")
    public void send() throws ExecutionException, InterruptedException {

        ListenableFuture<SendResult<String, String>> listenableFuture = this.producer.sendMessage(TOPIC_NAME, KEY, LocalDate.now().toString());

        SendResult<String, String> result = listenableFuture.get();
        logger.info(String.format("Produced:\ntopic: %s\noffset: %d\npartition: %d\nvalue size: %d", result.getRecordMetadata().topic(),
                result.getRecordMetadata().offset(),
                result.getRecordMetadata().partition(), result.getRecordMetadata().serializedValueSize()));
    }
}
