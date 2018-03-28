package io.cgrings.receiver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.cgrings.tracker.model.PageViewInput;

@Service
public class PageServiceImpl implements PageService {

	private Logger logger = LoggerFactory.getLogger(PageService.class);

	@Autowired
	private AmqpTemplate amqpTemplate;

    @Autowired
    private Queue pageHitQueue;

    @Override
    @Transactional
    public void sendToQueue(final PageViewInput pageViewInput) {
        final String routingKey = pageHitQueue.getName();
        logger.info("Sending Page View message to AMPQ with routingKey: '{}'.", routingKey);
        amqpTemplate.convertAndSend(routingKey, pageViewInput);
        logger.debug("Page View message successfull sended to AMPQ.");
    }

}
