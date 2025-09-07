package com.reviewms.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EventService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class); 
	
	@Autowired
	private ReviewService service;
	
	@KafkaListener(id = "prod_del_listen", topics = "product_delete_event")
	public void productDeleted(ConsumerRecord<String, String> record) {
		LOGGER.info("Product Delete Event received for {} : {}", record.key(), record.value());
		service.removeReviewsByProductId(record.value());
		LOGGER.info("Reviews deleted for {} : {}", record.key(), record.value());
	}

}
