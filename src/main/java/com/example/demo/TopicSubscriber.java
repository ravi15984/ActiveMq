package com.example.demo;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.json.JSONObject;

public class TopicSubscriber {

	public static void main(String[] args) {
		ConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");
		try {
		Connection connection = factory.createConnection();
		Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createTopic("demo-topic");
		MessageProducer producer = session.createProducer(destination);
		
		JSONObject message = new JSONObject();
		message.put("fromDateDEmo", new Date());
		message.put("toDateDemo", "2020-10-10");
		message.put("nameDemo", "Ravi");
		message.put("mobDemo", "98711111");
		
		//for (String mess : messages) {
			TextMessage text = session.createTextMessage(message.toString());
			producer.send(text); 
		//}
		session.close();
		connection.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
