package ru.sbsoft.kafka_consumer.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.sbsoft.dto.OperationParam;

@Component
@Slf4j
public class KafkaListenerComponent {

    private boolean error = false;

    @KafkaListener(topics = "topic-1", groupId = "primary", containerFactory = "userKafkaListenerContainerFactory")
    @Transactional
    public void listener1(@Payload OperationParam data, Acknowledgment acknowledgment) {
        log.info("Received message [{}] from topic-1", data);
//        error = true;
        if (error) {
            throw new RuntimeException("test");
        }
        //manual commit
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "topic-2", groupId = "primary", containerFactory = "userKafkaListenerContainerFactory")
    public void listener2(OperationParam data) {
        log.info("Received message [{}] from topic-2", data);
    }

}
