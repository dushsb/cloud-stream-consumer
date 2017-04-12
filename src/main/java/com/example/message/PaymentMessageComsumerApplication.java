package com.example.message;


import java.util.logging.Logger;

import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

import com.example.message.channels.PaymentSubscriberChannel;



@EnableBinding(PaymentSubscriberChannel.class)
@SpringBootApplication
public class PaymentMessageComsumerApplication {

	@Bean
	@Scope("prototype")
	Logger logger(InjectionPoint ip){
		return Logger.getLogger(ip.getDeclaredType().getName());
	}
	
	@Bean
	IntegrationFlow integrationFlow(Logger logger, PaymentSubscriberChannel consumerChannel){
		return IntegrationFlows
				.from(consumerChannel.paymentProducerChannel())
				.handle(String.class, (payload,headder) -> {
					logger.info("Message Received " + payload);
					return null;
				})
				.get();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(PaymentMessageComsumerApplication.class, args);
	}
}
