package io.cgrings.receiver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.cgrings.tracker.model.ContactInput;

@Service
public class ContactServiceImpl implements ContactService {

	private Logger logger = LoggerFactory.getLogger(ContactService.class);

	@Autowired
	private AmqpTemplate amqpTemplate;

    @Autowired
    private Queue contactQueue;

    @Override
    @Transactional
    public void sendToQueue(final ContactInput contactInput) {
        final String routingKey = contactQueue.getName();
        logger.info("Sending Contact message to AMPQ with routingKey: '{}'.", routingKey);
        amqpTemplate.convertAndSend(routingKey, contactInput);
        logger.debug("Contact message successfull sended to AMPQ.");
    }

}
