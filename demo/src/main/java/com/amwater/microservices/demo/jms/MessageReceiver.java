package com.amwater.microservices.demo.jms;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
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
public class MessageReceiver {
	private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);
	
	private static final Long receiveTimeout = 2000L;
	
	@Value("${jms.uri}")
	private String jmsUri;

	@Value("${jms.queue}")
	private String jmsQueue;

    public String receiveTextMessage() throws Exception {
    	String returnMessage = null;
    	Connection connection = null;
    	
        try {
    		JmsConnectionFactory factory = new JmsConnectionFactory(jmsUri);

    		Destination queue = new JmsQueue(jmsQueue);
    		
            connection = factory.createConnection(System.getProperty("USER"), System.getProperty("PASSWORD"));
            connection.setExceptionListener(new MyExceptionListener());
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            MessageConsumer messageConsumer = session.createConsumer(queue);

            TextMessage receivedMessage = (TextMessage) messageConsumer.receive(receiveTimeout);

            if (receivedMessage != null) {
                returnMessage = receivedMessage.getText();
            }
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        	
        	throw(e);
        } finally {
        	if (connection != null) {
        		connection.close();
        	}
        }
        
        return returnMessage;
    }

    public Object receiveObjectMessage() throws Exception {
    	Object returnMessage = null;
    	Connection connection = null;
    	
        try {
    		JmsConnectionFactory factory = new JmsConnectionFactory(jmsUri);

    		Destination queue = new JmsQueue(jmsQueue);
    		
            connection = factory.createConnection(System.getProperty("USER"), System.getProperty("PASSWORD"));
            connection.setExceptionListener(new MyExceptionListener());
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            MessageConsumer messageConsumer = session.createConsumer(queue);

            ObjectMessage receivedMessage = (ObjectMessage) messageConsumer.receive(receiveTimeout);

            if (receivedMessage != null) {
                returnMessage = receivedMessage.getObject();
            }
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        	
        	throw(e);
        } finally {
        	if (connection != null) {
        		connection.close();
        	}
        }
        
        return returnMessage;
    }

    private static class MyExceptionListener implements ExceptionListener {
        @Override
        public void onException(JMSException e) {
        	logger.error(e.getMessage(), e);
        }
    }
}
