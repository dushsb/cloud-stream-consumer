package com.example.message.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface PaymentSubscriberChannel {

	@Input
	SubscribableChannel paymentProducerChannel();
}
