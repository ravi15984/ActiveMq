package com.example.demo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Publisher {

	public static void main(String[] args) {
		ConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");
		try {
		Connection connection = factory.createConnection();
		Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("demo");
		MessageProducer producer = session.createProducer(destination);
		
		String [] messages = {"First message","second message","third message"};
		for (String mess : messages) {
			TextMessage text = session.createTextMessage(mess);
			producer.send(text); 
		}
		session.close();
		connection.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
