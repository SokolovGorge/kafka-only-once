package ru.sbsoft.kafka_producer.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.sbsoft.dto.OperationParam;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaSender {

    private final KafkaTemplate<String, OperationParam> kafkaTemplate;

    public void sendMessage(String topicName, OperationParam message) {
        log.info("sending message: " + message);
        kafkaTemplate.send(topicName, message);
    }

}
