package com.amwater.microservices.demo.jms;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.apache.qpid.jms.JmsQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
	private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);
	
	@Value("${jms.uri}")
	private String jmsUri;

	@Value("${jms.queue}")
	private String jmsQueue;

    public void sendMessage(String message) throws Exception {
    	Connection connection = null;
    	
    	try {
    		JmsConnectionFactory factory = new JmsConnectionFactory(jmsUri);

    		Destination queue = new JmsQueue(jmsQueue);
    		
            connection = factory.createConnection(System.getProperty("USER"), System.getProperty("PASSWORD"));
            connection.setExceptionListener(new MyExceptionListener());
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            MessageProducer messageProducer = session.createProducer(queue);

            TextMessage textMessage = session.createTextMessage(message);
            messageProducer.send(textMessage, DeliveryMode.NON_PERSISTENT, Message.DEFAULT_PRIORITY, Message.DEFAULT_TIME_TO_LIVE);
    	} catch (Exception e) {
        	logger.error(e.getMessage(), e);
        	
        	throw(e);
        } finally {
        	if (connection != null) {
        		connection.close();
        	}
        }
    }

    public void sendMessage(Object message) throws Exception {
    	Connection connection = null;
    	
    	try {
    		JmsConnectionFactory factory = new JmsConnectionFactory(jmsUri);

    		Destination queue = new JmsQueue(jmsQueue);
    		
            connection = factory.createConnection(System.getProperty("USER"), System.getProperty("PASSWORD"));
            connection.setExceptionListener(new MyExceptionListener());
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            MessageProducer messageProducer = session.createProducer(queue);

            ObjectMessage objMessage = session.createObjectMessage((Serializable) message);
            
            messageProducer.send(objMessage, DeliveryMode.NON_PERSISTENT, Message.DEFAULT_PRIORITY, Message.DEFAULT_TIME_TO_LIVE);
    	} catch (Exception e) {
        	logger.error(e.getMessage(), e);
        	
        	throw(e);
        } finally {
        	if (connection != null) {
        		connection.close();
        	}
        }
    }

    private static class MyExceptionListener implements ExceptionListener {
        @Override
        public void onException(JMSException e) {
        	logger.error(e.getMessage(), e);
        }
    }
}
