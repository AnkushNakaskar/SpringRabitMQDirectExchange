package com.rabitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

	private final RabbitTemplate rabbitTemplate;
	private final DirectExchangeMessageConsumer receiver;
	private final ConfigurableApplicationContext context;

	public Runner(DirectExchangeMessageConsumer receiver, RabbitTemplate rabbitTemplate,
			ConfigurableApplicationContext context) {
		this.receiver = receiver;
		this.rabbitTemplate = rabbitTemplate;
		this.context = context;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Sending message...");
		System.out.println(
				".........No queue bound to exchange..hence message is discarded ....you can see exchange is different than queue name....");
		rabbitTemplate.convertAndSend("Hello from RabbitMQ!");
	}

}