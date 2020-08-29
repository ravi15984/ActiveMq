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

public class ReatTimeProducer {

	public static void main(String[] args) {
		ConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");
		try {
		Connection connection = factory.createConnection();
		Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("demo");
		MessageProducer producer = session.createProducer(destination);
		
		JSONObject message = new JSONObject();
		message.put("fromDate", new Date());
		message.put("toDate", "2020-10-10");
		message.put("name", "Ravi");
		message.put("mob", "98711111");
		
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

