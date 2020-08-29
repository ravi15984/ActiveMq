package com.example.demo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicCOnsumer {

	public static void main(String[] args) {
		
		ConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");
		try {
		Connection connection = factory.createConnection();
		connection.setClientID("2");
		connection.start();
		
		Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("demo-topic");
		MessageConsumer consumer1 = session.createDurableSubscriber(topic, "Consumer-2");
		consumer1.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				TextMessage textMessage = (TextMessage)message;
				try {
					System.out.println("Data COnsumed "+textMessage.getText());
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
