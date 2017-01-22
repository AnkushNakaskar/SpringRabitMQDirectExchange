package com.rabitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class DirectExchangeMessageConsumer2 implements MessageListener{

	@Override
	public void onMessage(Message message) {
		System.out.println("Direct Exchange....2");
		
	}

}
