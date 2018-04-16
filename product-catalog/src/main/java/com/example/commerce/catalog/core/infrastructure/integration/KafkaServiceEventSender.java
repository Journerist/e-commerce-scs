package com.example.commerce.catalog.core.infrastructure.integration;

import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Sends global events to other contexts. These events usually contain data that
 * will be duplicated in their application to avoid runtime dependencies.
 * 
 * <p>Domain events should never be sent to other services. They should be mapped
 * to an service event to avoid core domain event coupling to external apis.
 * 
 * @author Sebastian Barthel
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaServiceEventSender {

    private final KafkaOperations<String, String> kafka;

    /**
     * TODO: remove this on listener because it sends all events to kafka. Only a
     * service events should be redirected.
     * 
     * @param event
     */
    @EventListener
    public void on(Object event) {
        log.info("Send service event: " + event);
        send(event);
    }

    private void send(Object event) {
        String payload = event.getClass().getName();
        kafka.send("test", payload);
        log.info("Publishing {} to Kafka", payload);
    }

}
