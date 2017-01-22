package com.rabitmq;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	final static String queueName = "spring-boot-direct1";
	
	@Autowired
	private DirectExchangeMessageConsumer consumer;

	
	@Bean
	ConnectionFactory factory(){
		CachingConnectionFactory fact =new CachingConnectionFactory();
		fact.setAddresses("localhost");
		fact.setUsername("guest");
		fact.setPassword("guest");
		return fact;
	}
	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange("spring-boot-exchange");
	}

	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(queueName);
	}

	@Bean
	SimpleMessageListenerContainer container() {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(factory());
		container.setQueueNames(queueName);
		container.setMessageListener(consumer);
		return container;
	}


	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Application.class, args);
	}

}