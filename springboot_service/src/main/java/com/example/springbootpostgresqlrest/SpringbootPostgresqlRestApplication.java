package com.example.springbootpostgresqlrest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
@EnableJpaAuditing
public class SpringbootPostgresqlRestApplication {

	private final static String QUEUE_NAME = "main_queue";

	@Bean
	public static void ListenChannel() throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
			System.out.println(" [x] Received '" + message + "'");
		};
		channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootPostgresqlRestApplication.class, args);
	}
}
